# PromotionEngine
Calculates the order total by applying promotion discounts

**Applicable articles to be added in the cart :** A/B/C/D

**Active Promotions :**

Buy 3 A's for 130

Buy 2 B's for 45 

Buy C & D for 30

# Input request json format
{
"items": [
{
"skuId": "A",
"quantity": 5
}
]
}

# OutPut response json format

{
"orderTotalWithoutPromotion": 0,
"orderTotalWithPromotion": 0,
"savings": 0
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