def call() {
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