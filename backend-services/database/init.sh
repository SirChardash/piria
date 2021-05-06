#!/bin/sh
cat /init/*.sql | mysql --password=${MYSQL_ROOT_PASSWORD} --user=root
