#!/bin/bash


source ~/twitter_tokens.sh
export spring_profiles_active="dev"

java -jar ./target/TwitterCLI.jar "$@"
