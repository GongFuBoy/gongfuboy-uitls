#!/bin/bash

cd ../../..
mvn clean deploy -Dmaven.test.skip=true -P sonatype-oss-release -Darguments="gpg.passphrase=$1"