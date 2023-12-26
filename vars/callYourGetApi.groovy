@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')
import groovyx.net.http.RESTClient


def callYourGetApi(String jobName) {
    try {
        def restClientScript = new File("/home/pravallika/Jekins-event-listener/groovy-events-listener-plugin/src/main/site/includes/RestClient.groovy")
        def restClientClass = evaluate(restClientScript)

        def getRestClient = new RESTClient('http://localhost:8096')
        def headers = [
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Accept-Encoding': 'gzip, deflate, br',
            'Accept-Language': 'en-GB,en-US;q=0.9,en;q=0.8',
        ]
        getRestClient.headers = headers

        // Customize the path based on your API and include parameters
        def response = getRestClient.get(
            path: "/visibilityservice/v6/getJenkinsApproval?jobName=${URLEncoder.encode(jobName, 'UTF-8')}"
        )

        // Assuming the API returns a simple value like 'yes' or 'no'
        return response.data.toString()
    } catch (Exception e) {
        // Handle exceptions, log, or return a default value
        return 'no'
    }
}

