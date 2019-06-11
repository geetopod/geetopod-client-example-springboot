![geetoPod - Identiy Solutions](https://github.com/geetopod/geetopod/raw/master/resources/images/geetopod-banner-96.png)

# Spring Boot Client Example

## Install Google Cloud Console to MacOS

Ref. [Quickstart for macOS](https://cloud.google.com/sdk/docs/quickstart-macos)

Install Kubectl:
```
gcloud components install kubectl

gcloud auth configure-docker
```

## Deploy to GCP

Modify folowing lines in d-exec.sh:
```
export defaultGcpProject=geetopod
export defaultGcpCluster=geetopod
export defaultGcpZone=us-central1-a
```

Modify following lines in deploy-to-gcp.yaml:
```
        - name: SSO_GATEWAY_URL
          value: "http://gw.geetopod.com"
        - name: WEB_URL
          value: "http://ceg-springboot.geetopod.com"
        - name: SSO_COMPANY
          value: "geetopod-developers"
        - name: IS_DEBUG
          value: "false"
        - name: SPRING_SESSION_TIMEOUT
          value: "360000"


    spec:
      containers:
      - image: gcr.io/geetopod/geetopod-client-example-springboot:0.1.3

```
==> Modify geetopod with GCP project name, 0.1.3 with build version

Run: (for example version is 0.1.3)
```
./d-exec.sh build 0.1.3

./d-exec.sh pushtogcp 0.1.3

./d-exec.sh deploytogcp 0.1.3
```

