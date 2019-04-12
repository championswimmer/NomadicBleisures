package com.booking.bookingapi.models

import com.google.gson.annotations.SerializedName

data class RoomData(
    @SerializedName("room_description")
    var roomDescription: String,
    @SerializedName("room_facilities")
    var roomFacilities: List<RoomFacility>,
    @SerializedName("room_id")
    var roomId: Int,
    @SerializedName("room_info")
    var roomInfo: RoomInfo,
    @SerializedName("room_name")
    var roomName: String,
    @SerializedName("room_photos")
    var roomPhotos: List<RoomPhoto>
) {
    data class RoomInfo(
        @SerializedName("bathroom_count")
        var bathroomCount: Int,
        @SerializedName("bed_configurations")
        var bedConfigurations: List<BedConfiguration>,
        @SerializedName("bedroom_count")
        var bedroomCount: Int,
        @SerializedName("bedrooms")
        var bedrooms: List<Bedroom>,
        @SerializedName("bookable")
        var bookable: String,
        @SerializedName("max_persons")
        var maxPersons: Int,
        @SerializedName("max_price")
        var maxPrice: Int,
        @SerializedName("min_price")
        var minPrice: Int,
        @SerializedName("ranking")
        var ranking: Int,
        @SerializedName("room_size")
        var roomSize: RoomSize,
        @SerializedName("room_type")
        var roomType: String,
        @SerializedName("room_type_id")
        var roomTypeId: Int,
        @SerializedName("roomtype")
        var roomtype: String
    ) {
        data class RoomSize(
            @SerializedName("metre_square")
            var metreSquare: Int
        )

        data class Bedroom(
            @SerializedName("description")
            var description: String,
            @SerializedName("name")
            var name: String
        )

        data class BedConfiguration(
            @SerializedName("bed_types")
            var bedTypes: List<BedType>
        ) {
            data class BedType(
                @SerializedName("configuration_id")
                var configurationId: String,
                @SerializedName("count")
                var count: Int,
                @SerializedName("description")
                var description: String,
                @SerializedName("description_imperial")
                var descriptionImperial: String,
                @SerializedName("name")
                var name: String
            )
        }
    }

    data class RoomFacility(
        @SerializedName("name")
        var name: String,
        @SerializedName("room_facility_type_id")
        var roomFacilityTypeId: Int
    )

    data class RoomPhoto(
        @SerializedName("auto_tags")
        var autoTags: List<AutoTag>,
        @SerializedName("main_photo")
        var mainPhoto: String,
        @SerializedName("tags")
        var tags: List<String>,
        @SerializedName("url_max300")
        var urlMax300: String,
        @SerializedName("url_original")
        var urlOriginal: String,
        @SerializedName("url_square60")
        var urlSquare60: String
    ) {
        data class AutoTag(
            @SerializedName("tag_id")
            var tagId: String,
            @SerializedName("tag_name")
            var tagName: String
        )
    }
}