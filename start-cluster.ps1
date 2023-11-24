param([switch] $loadImages )

Clear-Host

kubectl config use-context kind-kind

kubectl delete all --all -n default

if ($loadImages) {
    Write-Host "Starting load images to cluster"
    kind load docker-image guardian/discovery:0.0.1-SNAPSHOT -q
    kind load docker-image guardian/micro-api-alarm:0.0.1-SNAPSHOT -q
    kind load docker-image guardian/micro-api-operator:0.0.1-SNAPSHOT -q
    kind load docker-image guardian/micro-scheduler:0.0.1-SNAPSHOT -q
    kind load docker-image guardian/micro-ui:0.0.1-SNAPSHOT -q
    kind load docker-image guardian/prometheus:0.0.1-SNAPSHOT -q
    kind load docker-image guardian/grafana:0.0.1-SNAPSHOT -q
    Write-Host "Images was loaded to cluster"
}
else {
    Write-Host "No new images was loaded to cluster"
}

kompose convert -f docker-compose-apps.yml -o .\Kubernetes\Kompose

kubectl apply -f .\Kubernetes\Kompose --recursive

Write-Host "Objects was deployed to cluster!"