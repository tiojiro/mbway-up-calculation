## What I did
:REQUIRED:
:EXAMPLE:
- Changed the method X
- Sending values to service Y
## How to verify it
### Request
```bash
curl -X GET "http://localhost:8080/proposals/1000013884/data" -H "accept: */*" -H "Authorization: Bearer SEU_BEARER_TOKEN"
  --compressed
```
### Response expected
```json
{
  "dueDate": "0001-01-01",
  "amount": 12,
  "group": 0,
  "customer": 976,
  "proposalType": "PNG",
  "proposalSubtype": "NOV"
}
```