# Requires powershell
# Requires java being present in %PATH

java -jar swagger-codegen-cli.jar generate -i https://corona.lmao.ninja/apidocs/swagger_v3.json -l java -o generatedClient -c .\config.json -s false


