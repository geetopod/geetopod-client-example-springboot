#!/bin/sh

export defaultGcpProject=geetopod
export defaultGcpCluster=geetopod
export defaultGcpZone=us-central1-a
export defaultGcpDeployment=geetopod-client-example-springboot
export mainDockerfile=ceg.dockerfile
export defaultImageName=geetopod-client-example-springboot
export version=0.1.1
export defaultGcpImageName=gcr.io/$defaultGcpProject/$defaultGcpDeployment
export gcpDeployYaml=deploy-to-gcp.yaml


if [ “$1” = “build” ]
then
  if [ “$2” != “” ]
  then
    export version=$2
  fi
  echo “docker build -f $mainDockerfile -t $defaultImageName:$version .”
  docker build -f $mainDockerfile -t $defaultImageName:$version .
fi

if [ “$1” = “pushtogcp” ]
then
  if [ “$2” != “” ]
  then
    export version=$2
  fi
  echo “docker tag $defaultImageName:$version $defaultGcpImageName:$version ”
  docker tag $defaultImageName:$version $defaultGcpImageName:$version 
  echo “gcloud config set project $defaultGcpProject ”
  gcloud config set project $defaultGcpProject 
  echo “docker push $defaultGcpImageName:$version ”
  docker push $defaultGcpImageName:$version 
fi

if [ “$1” = “deploytogcp” ]
then
  if [ “$2” != “” ]
  then
    export version=$2
  fi
  echo “gcloud config set project $defaultGcpProject “
  gcloud config set project $defaultGcpProject 
  echo “gcloud container clusters get-credentials $defaultGcpCluster --zone $defaultGcpZone --project $defaultGcpProject “
  gcloud container clusters get-credentials $defaultGcpCluster --zone $defaultGcpZone --project $defaultGcpProject 
  echo “kubectl apply -f $gcpDeployYaml “
  kubectl apply -f $gcpDeployYaml 

fi
