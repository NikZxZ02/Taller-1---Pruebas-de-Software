## Alma Mater

Alma mater use KNN recommender algorithm for recommend an University

## Run locally

- Build Docker image:
  `docker build -t alma .`

- Run server:
  `docker run --rm -it -p 8080:8080 alma:latest`

## API Docs

### Universities

- Get universities:

`GET /universities`

#### Response example:

```js
[
  {
    id: 1,
    name: 'Universidad de Chile',
    region: 0,
    url: null,
    urlEscudo: null,
    ptsBca: 5,
    ptsAcr: 5,
    ptsRank: 5,
    isPrivate: '0',
    rankUniver: 0,
    anosAcr: 0,
    ptsNear: 0,
    distanceKNN: 0.0,
    lat: -33.4445204,
    lng: -70.6509277,
    distanceLatLng: 0.0,
    careers: null,
    pointsWithNear: [5.0, 5.0, 5.0, 0.0],
    points: [5.0, 5.0, 5.0],
    pointsSum: 15.0,
  },
  ...
];
```

### Careers

- Get Careers:

`GET /careers`

#### Response example:

```js
[
  {
    id: 1,
    name: 'Universidad de Chile',
  },
  ...
];
```

### Surveys:

- Run survey

`POST /survey`

#### Body Example:

```js
{
	"id": 1,
	"ptsBca": 1,
	"ptsAcr": 1,
	"ptsRank": 1,
	"region": 1,
	"isPrivate": false,
	"ptsNear": 1,
	"lat": 1,
	"lng": 2,
	"careerId": 2
}
```

#### Response example:

```js
[
  {
    id: 1,
    name: 'Universidad de Chile',
  },
  ...
];
```
