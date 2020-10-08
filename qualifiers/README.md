# Qualifiers

A `CDI qualifier` is an annotation to indicate the kind of a bean the class is. It uses to determine to CDI which bean implementation should be used at an injection point when multiple implementations exist. To demonstrate this behavior, this repository contains a VAT calculation example.

```shell script
curl -XPOST -H "Content-Type: application/json" http://localhost:9080/api/vat -d '{
"name": "MacBook Pro",
"price": "10500",
"type": "LUXURY_CONSUMPTION"}'

2625.00

curl -XPOST -H "Content-Type: application/json" http://localhost:9080/api/vat -d '{
"name": "Samsung UE50TU7000UXTK 50",
"price": "4500",
"type": "HOUSEHOLD_APPLIANCES"}'

810.00
```
