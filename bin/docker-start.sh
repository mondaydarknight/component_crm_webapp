#!/bin/bash

mkdir -p /opt/app/
ln -s /tmp/target/component_crm_webapp-0.0.1-SNAPSHOT.jar /opt/app

echo "---------------------------------------"
echo "          Smartbee CRM webapp          "
echo "---------------------------------------"

java -jar /opt/app/component_crm_webapp-0.0.1-SNAPSHOT.jar spring.profiles.active=docker --debug

tail -f /dev/null
