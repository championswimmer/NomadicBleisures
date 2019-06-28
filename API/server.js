const express = require('express')
const { Cities, BookingCities, Countries } = require('./db')
const { Op } = require('sequelize')
const Sequelize = require('sequelize')

const app = express()

app.use('/cities', require('./routes/cities').route)
app.use('/bleisures', require('./routes/bleisures').route)
app.use('/meta', require('./routes/meta').route)

app.listen(4242, () => {
  console.log('Server started on http://localhost:4242')
})