#!/bin/bash
UUID_REGEX='[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}'

curl -u admin:admin -X POST -H "Content-Type: application/json" -d @prometheus.json "http://localhost:3000/api/datasources"
echo
UUID=$(curl -sS -u admin:admin "http://localhost:3000/api/datasources" | grep -oE "$UUID_REGEX")
sed "s/%DATASOURCE%/$UUID/" micrometer.json.in > micrometer.json
curl -u admin:admin -X POST -H "Content-Type: application/json" -d @micrometer.json "http://localhost:3000/api/dashboards/db"
echo
