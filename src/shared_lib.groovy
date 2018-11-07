
def unix_compile_script(node_name) {
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

def win_compile_script() {
    node('windows') {
		checkout scm
		String vsvars_bat = 'Microsoft Visual Studio 12.0\\VC\\vcvarsall.bat'
		bat """
		    call "%ProgramFiles(X86)%\\${vsvars_bat}" x86
		    cl.exe helloworld.c
		"""

		stash name: "build_win", includes: "*.exe"
		deleteDir()
	}
}