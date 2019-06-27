const Sequelize = require('sequelize')
const config = require('./config.json')
const db = new Sequelize({
  dialect: 'mysql',
  database: config.DB.NAME,
  host: config.DB.SEVER,
  username: config.DB.USER,
  password: config.DB.PASSWORD
})

db.authenticate().then(() => {
  console.log('DB Authenticated')
}).catch(e => {
  throw e
})

const Countries = db.define('country', {
  code: { type: Sequelize.STRING(2), primaryKey: true },
  name: Sequelize.STRING,
  region: Sequelize.STRING
}, {
  timestamps: false
})


const Cities = db.define('city', {
  slug: { type: Sequelize.STRING, unique: true },
  countryId: { type: Sequelize.STRING(2) },
  description: Sequelize.TEXT,
  aqi: Sequelize.INTEGER, // air_quality
  internetMbps: Sequelize.INTEGER,
  latitude: Sequelize.DECIMAL(10, 8),
  longitude: Sequelize.DECIMAL(11,8),
  population: Sequelize.BIGINT,
  safetyLevel: Sequelize.INTEGER, // safety_level
  userBeen: Sequelize.INTEGER, // users_been_count
  livingCost: Sequelize.INTEGER, // short_term_cost_in_usd
}, {
  timestamps: false
})


module.exports = {
  db,
  Countries,
  Cities
}