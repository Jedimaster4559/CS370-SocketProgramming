set run_mode="%1"

if %run_mode% == "client" goto :start_client
if %run_mode% == "server" goto :start_server
goto :eof

:start_client
java -jar Client/target/Client-1.0-SNAPSHOT.jar
goto :eof

:start_server
java -jar Server/target/Server-1.0-SNAPSHOT.jar
goto :eof

:eof