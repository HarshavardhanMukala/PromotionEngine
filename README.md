# PromotionEngine
Calculates the order total by applying promotion discounts

# Input request format
{
"items": [
{
"skuId": "A",
"quantity": 5
}
]
}

# Example :
{
"items": [
{
"skuId": "A",
"quantity": 5
},
{
"skuId": "B",
"quantity": 5
},
{
"skuId": "C",
"quantity": 5
},
{
"skuId": "D",
"quantity": 10
}
]
}

Request and Response tested in postman: 

# Request

{
"items": [
{
"skuId": "A",
"quantity": 5
},
{
"skuId": "B",
"quantity": 5
},
{
"skuId": "C",
"quantity": 5
},
{
"skuId": "D",
"quantity": 10
}
]
}

# Response

{
"orderTotalWithoutPromotion": 650,
"orderTotalWithPromotion": 500,
"savings": 150
}