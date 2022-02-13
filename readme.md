URL : 
* swagger url : http://127.0.0.1:8080/swagger-ui/index.html?configUrl=/v3/api-docs/  
* graphiql url : http://127.0.0.1:8080/graphiql  
  
Exemples de requêtes :   
* Création d'une offre  

 ```
curl --location --request PUT '127.0.0.1:8080/offer/offer' \  
--header 'Content-Type: application/json' \  
--data-raw '{  
        "type" : "PRIMARY",   
        "offerBlocs" : [  
  
            {  
                "timeslot" : "SLOT_6_9",  
                "floorPrice" : "12.12"  
            },  
            {  
                "timeslot" : "SLOT_21_24",  
                "floorPrice" : "15.12"  
            }  
        ]  
}'  
```
  
* Création d'un parc   
```
curl --location --request PUT '127.0.0.1:8080/park/park' \  
--header 'Content-Type: application/json' \  
--data-raw '{  
    "name": "jj",  
    "energyType": "ELECTRICITY",  
    "producedBlocs": {  
        "SLOT_6_9": 125.01,  
        "SLOT_12_15": -126.01  
    }  
}'  
```

* Lister les offres   
 ```
 curl --location --request GET '127.0.0.1:8080/offer/offers' \  
--header 'Content-Type: application/json' \  
--data-raw ''
 ```  
  
* Exemple de requete graphql   
 ```
 curl 'http://127.0.0.1:8080/graphql' -H 'Accept-Encoding: gzip, deflate, br' -H 'Content-Type: application/json' -H 'Accept: application/json' -H 'Connection: keep-alive' -H 'Origin: altair://-' --data-binary '{"query":"query{\n  offer {\n    type\n    offerBlocs {\n      timeslot\n      floorPrice\n      availableBlocs {\n        name\n        energyType\n        quantity\n        timeSlot\n      }\n    }\n  }\n}","variables":{}}' --compressed
  ```
