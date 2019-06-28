const Sequelize = require('sequelize')
const config = require('./config.json')
const db = new Sequelize({
  dialect: 'mysql',
  database: config.DB.NAME,
  host: config.DB.SEVER,
  username: config.DB.USER,
  password: config.DB.PASSWORD,
  dialectOptions: {
    charset: 'utf8mb4',
    collate: 'utf8mb4_general_ci'
  },
  define: {
    charset: 'utf8',
    collate: 'utf8_general_ci',
    timestamps: false
  }
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
  name: Sequelize.STRING,
  slug: { type: Sequelize.STRING, unique: true },
  countryId: { type: Sequelize.STRING(2) },
  description: Sequelize.TEXT,
  aqi: Sequelize.INTEGER, // air_quality
  internetMbps: Sequelize.INTEGER,
  latitude: Sequelize.DECIMAL(10, 8),
  longitude: Sequelize.DECIMAL(11, 8),
  population: Sequelize.BIGINT,
  safetyLevel: Sequelize.INTEGER, // safety_level
  userBeen: Sequelize.INTEGER, // users_been_count
  livingCost: Sequelize.INTEGER, // short_term_cost_in_usd,
  tempC: Sequelize.INTEGER,
  tempCfeels: Sequelize.INTEGER,
  image: Sequelize.STRING,
  score: Sequelize.FLOAT,
  weatherEmoji: Sequelize.STRING
}, {
  timestamps: false
})


Cities.belongsTo(Countries, { foreignKey: 'countryId' })
Countries.hasMany(Cities, { foreignKey: 'countryId' })

const BookingCities = db.define('booking_city', {
  id: { type: Sequelize.BIGINT, primaryKey: true },
  url: Sequelize.STRING,
  label: Sequelize.STRING
}, {
  timestamps: false
})

BookingCities.belongsTo(Cities)
Cities.hasMany(BookingCities)

const Coworks = db.define('coworking_places', {
  id: { type: Sequelize.INTEGER, primaryKey: true },
  city_id: Sequelize.INTEGER,
  name: Sequelize.STRING,
  m_price: Sequelize.INTEGER,
  d_price: Sequelize.INTEGER,
  currency: Sequelize.STRING(4),
  rating: Sequelize.DECIMAL,
  image_url: Sequelize.STRING,
  latitude: Sequelize.DECIMAL(10, 8),
  longitude: Sequelize.DECIMAL(11, 8),
}, {
  freezeTableName: true
})

Coworks.belongsTo(Cities, { foreignKey: 'city_id' })
Cities.hasMany(Coworks, { foreignKey: 'city_id' })

module.exports = {
  db,
  Countries,
  Cities,
  BookingCities,
  Coworks
}