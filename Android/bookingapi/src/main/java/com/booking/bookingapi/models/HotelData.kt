package com.booking.bookingapi.models

import com.google.gson.annotations.SerializedName

data class HotelData(
    @SerializedName("address")
    var address: String,
    @SerializedName("book_domestic_without_cc_details")
    var bookDomesticWithoutCcDetails: String,
    @SerializedName("chain_id")
    var chainId: List<Int>,
    @SerializedName("checkin_checkout_times")
    var checkinCheckoutTimes: CheckinCheckoutTimes,
    @SerializedName("city")
    var city: String,
    @SerializedName("city_id")
    var cityId: Int,
    @SerializedName("class")
    var classX: String,
    @SerializedName("class_is_estimated")
    var classIsEstimated: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("creditcard_required")
    var creditcardRequired: Boolean,
    @SerializedName("currency")
    var currency: String,
    @SerializedName("deep_link_url")
    var deepLinkUrl: String,
    @SerializedName("default_language")
    var defaultLanguage: String,
    @SerializedName("district_id")
    var districtId: Int,
    @SerializedName("email")
    var email: String,
    @SerializedName("exact_class")
    var exactClass: String,
    @SerializedName("hotel_description")
    var hotelDescription: String,
    @SerializedName("hotel_facilities")
    var hotelFacilities: List<HotelFacility>,
    @SerializedName("hotel_important_information")
    var hotelImportantInformation: String,
    @SerializedName("hotel_photos")
    var hotelPhotos: List<HotelPhoto>,
    @SerializedName("hotel_policies")
    var hotelPolicies: List<HotelPolicy>,
    @SerializedName("hotel_type_id")
    var hotelTypeId: Int,
    @SerializedName("hotelier_welcome_message")
    var hotelierWelcomeMessage: String,
    @SerializedName("is_closed")
    var isClosed: String,
    @SerializedName("key_collection_info")
    var keyCollectionInfo: KeyCollectionInfo,
    @SerializedName("license_number")
    var licenseNumber: String,
    @SerializedName("location")
    var location: Location,
    @SerializedName("max_persons_in_reservation")
    var maxPersonsInReservation: String,
    @SerializedName("max_rooms_in_reservation")
    var maxRoomsInReservation: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("number_of_reviews")
    var numberOfReviews: Int,
    @SerializedName("number_of_rooms")
    var numberOfRooms: Int,
    @SerializedName("payment_details")
    var paymentDetails: List<PaymentDetail>,
    @SerializedName("preferred")
    var preferred: String,
    @SerializedName("ranking")
    var ranking: Int,
    @SerializedName("review_score")
    var reviewScore: Double,
    @SerializedName("spoken_languages")
    var spokenLanguages: List<String>,
    @SerializedName("theme_ids")
    var themeIds: List<Int>,
    @SerializedName("url")
    var url: String,
    @SerializedName("zip")
    var zip: String
) {
    data class CheckinCheckoutTimes(
        @SerializedName("checkin_from")
        var checkinFrom: String,
        @SerializedName("checkin_to")
        var checkinTo: String,
        @SerializedName("checkout_from")
        var checkoutFrom: String,
        @SerializedName("checkout_to")
        var checkoutTo: String
    )

    data class PaymentDetail(
        @SerializedName("bookable")
        var bookable: String,
        @SerializedName("cvc_required")
        var cvcRequired: String,
        @SerializedName("payable")
        var payable: String,
        @SerializedName("payment_id")
        var paymentId: String
    )

    data class KeyCollectionInfo(
        @SerializedName("alternative_key_location")
        var alternativeKeyLocation: AlternativeKeyLocation,
        @SerializedName("how_to_collect")
        var howToCollect: String,
        @SerializedName("key_location")
        var keyLocation: String,
        @SerializedName("other_text")
        var otherText: String
    ) {
        data class AlternativeKeyLocation(
            @SerializedName("address")
            var address: String,
            @SerializedName("city")
            var city: String,
            @SerializedName("zip")
            var zip: String
        )
    }

    data class HotelFacility(
        @SerializedName("attrs")
        var attrs: List<String>,
        @SerializedName("hotel_facility_type_id")
        var hotelFacilityTypeId: Int,
        @SerializedName("name")
        var name: String
    )

    data class HotelPhoto(
        @SerializedName("auto_tags")
        var autoTags: List<AutoTag>,
        @SerializedName("is_logo_photo")
        var isLogoPhoto: String,
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

    data class HotelPolicy(
        @SerializedName("content")
        var content: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("type")
        var type: String
    )

    data class Location(
        @SerializedName("latitude")
        var latitude: String,
        @SerializedName("longitude")
        var longitude: String
    )
}