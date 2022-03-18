# 💁 Customer Service

Customer Service is a simple spring boot REST api that serves you a list of customers!

## Model
### Customer  
|  attribute  |  type   | required  |
|-------------|---------|-----------|
| firstName   | String  |    Yes    |
| lastName    | String  |    Yes    |
| companyName | String  |    Yes    |

## Endpoints
* GET v1/customers/
  * Returns a list of Customer objects
  * Available Querys:
  *    name: String, the prefix of the first or last name of a customer
  *    companyName: the exact match of a customers company name
  *    sortBy: one of "FIRST_NAME_ASCENDING" "FIRST_NAME_DESCENDING" "LAST_NAME_ASCENDING" "LAST_NAME_DESCENDING" "COMPANY_NAME_ASCENDING" "COMPANY_NAME_DESCENDING"
