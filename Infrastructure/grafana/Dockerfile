FROM grafana/grafana:latest

# Disable Login form or not
ENV GF_AUTH_DISABLE_LOGIN_FORM "true"
# Allow anonymous authentication or not
ENV GF_AUTH_ANONYMOUS_ENABLED "true"
# Role of anonymous user
ENV GF_AUTH_ANONYMOUS_ORG_ROLE "Admin"

COPY ./datasources  /etc/grafana/provisioning/datasources
COPY ./dashboards  /etc/grafana/provisioning/dashboards
