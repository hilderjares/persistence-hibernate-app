mvn-clean:
	~/Downloads/maven/bin/mvn clean

mvn-install:
	~/Downloads/maven/bin/mvn install

mvn-ci:
	~/Downloads/maven/bin/mvn clean install

mvn-exec:
	~/Downloads/maven/bin/mvn exec:java -Dexec.cleanupDaemonThreads=false -Dexec.mainClass="com.ufc.app.App"
