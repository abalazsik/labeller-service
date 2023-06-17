#!/bin/bash -e

function print_help() {
    echo "available options: run_local_neo4j build_n_run run_docker_compose run_dev_compose"
}

function run_local_neo4j() {
    sudo docker run --publish=7474:7474 --publish=7687:7687 --env NEO4J_AUTH=neo4j/77A18HL6 neo4j:4.4
}

function run_dev_compose() {
    sudo docker-compose -f dev-compose.yml up
}

function build_maven() {
    mvn clean install
}

function build_docker_compose() {
    sudo docker-compose build
}

function run_docker_compose() {
    sudo docker-compose -f compose.yml up
}

function build_n_run() {
    build_maven
    build_docker_compose
    run_docker_compose
}

if [ -z $1 ]
then
	 print_help
	 exit 0
fi

case $1 in
    "run_local_neo4j")
	run_local_neo4j ;;
    "build_n_run")
	build_n_run ;;
    "run_docker_compose")
        run_docker_compose ;;
    "run_local_loki")
        run_local_loki ;;
    "run_local_grafana")
        run_local_grafana ;;
    *)
	echo "Unknown option $1"
	print_help
	exit 1
esac