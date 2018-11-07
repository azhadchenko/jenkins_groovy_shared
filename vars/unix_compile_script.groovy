def call(node_name) {
	node(node_name) {
		checkout scm 	
		sh """
			#!/bin/bash
			gcc -o ${node_name}.out helloworld.c
		"""
		stash name: "build_${node_name}", includes: "*.out"
		deleteDir()
	}
}