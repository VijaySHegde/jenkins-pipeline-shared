def json = "http://ec2-3-15-148-45.us-east-2.compute.amazonaws.com:8090/rest/api/search?cql=space%3Dvijay%20AND%20type%3Dpage".toURL() getText(requestProperties: [Accept: 'application/json',
                            Authorization: 'Basic YXNobmltOmppcmFAMTIz'])
