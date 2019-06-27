const cities = require('./cities')
const { db, Cities, Countries } = require('../db')
const Sequelize = require('sequelize')

console.log(`cities = ${cities.length}`)
console.log(cities[ 0 ])

const weather = {}
async function insert() {

  for (let c of cities) {
    console.log(c)
    if (!weather[c.weather_icon]) {
      weather[c.weather_icon] = c.weather_emoji
    }
  }
}

async function check() {
  console.log(JSON.stringify(weather, null, 4))
}

insert().then(() => check())