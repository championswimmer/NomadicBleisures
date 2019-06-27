const express = require('express')

const app = express()

app.get('cities')

app.listen(4242, () => {
  console.log('Server started')
})