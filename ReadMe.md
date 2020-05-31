# Sum Metric Service

## Problem
Build a metric logging and reporting service which sums metrics by a time window for the most recent hour. 
You will build a lightweight web server that implements the two main APIs defined below.

## APIs

### POST metric

```text
Request
POST /metric/{key}
```
```json
{
  "value": 30
}
```
```text
Response (200)
```
```json
{}
```


### GET metric sum
Returns the sum of all metrics reported for this key the past hour
```text
Request
GET /metric/{key}/sum
```
```text
Response (200)
```
```json
{
  "value" : 400
}
```


#### Example
Imagine these are the events logged to your service for a metric "active_visitors"
```text
// 2 hours ago
POST /metric/active_visitors { "value" = 4 }

// 30 minutes ago
POST /metric/active_visitors { "value" = 3 }

// 40 seconds ago
POST /metric/active_visitors { "value" = 7 }

// 5 seconds ago
POST /metric/active_visitors { "value" = 2 }
```

These are the results expected from calling get aggregates:
```text
GET /metric/active_visitors/sum  // returns 12
```
