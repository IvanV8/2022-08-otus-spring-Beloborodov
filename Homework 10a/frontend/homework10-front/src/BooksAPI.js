
const api = "http://localhost:8080/api"

const headers = {
  'Accept': 'application/json'
}

export const getAll = () =>
  fetch(`${api}/books`, {headers})
    .then(res => res.json())
    .then(data => data.books)
/*
export const update = (book, shelf) =>
  fetch(`${api}/books/${book.id}`, {
    method: 'PUT',
    headers: {
      ...headers,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ shelf })
  }).then(res => res.json())

export const search = (query) =>
  fetch(`${api}/search`, {
    method: 'POST',
    headers: {
      ...headers,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ query })
  }).then(res => res.json())
    .then(data => data.books)
*/