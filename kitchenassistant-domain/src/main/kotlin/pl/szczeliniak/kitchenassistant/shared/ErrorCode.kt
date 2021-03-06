package pl.szczeliniak.kitchenassistant.shared

enum class ErrorCode(val message: String, val code: Int) {

    PASSWORDS_DO_NOT_MATCH("Passwords do not match", 400),
    UNSUPPORTED_MEDIA_TYPE("Unsupported media type", 400),
    FILE_TOO_LARGE("File too large", 413),
    FTP_UPLOAD_ERROR("Error while uploading to FTP", 424),
    FTP_DOWNLOAD_ERROR("Error while downloading from FTP", 424),
    FTP_DELETE_ERROR("Error while deleting from FTP", 424),
    FTP_FILE_CHECK_ERROR("Error while checking for file existence from FTP", 424),
    FTP_FILE_NOT_FOUND("File not found", 404),
    FTP_GENERIC_ERROR("Error on communication with FTP", 424),
    CATEGORY_ALREADY_REMOVED("Category is already marked as deleted!", 404),
    INGREDIENT_ALREADY_REMOVED("Ingredient is already marked as deleted!", 404),
    PHOTO_ALREADY_REMOVED("Photo is already marked as deleted!", 404),
    STEP_ALREADY_REMOVED("Step is already marked as deleted!", 404),
    RECEIPT_ALREADY_REMOVED("Receipt is already marked as deleted!", 404),
    SHOPPING_LIST_ALREADY_REMOVED("Shopping list is already marked as deleted!", 404),
    DAY_PLAN_ALREADY_REMOVED("Day plan is already deleted!", 404),
    DAY_PLAN_ALREADY_ARCHIVED("Day plan is already archived!", 404),
    SHOPPING_LIST_ITEM_ALREADY_REMOVED("Shopping list item is already marked as deleted!", 404),
    USER_ALREADY_EXISTS("User with email already exists", 409),
    RECEIPT_NOT_FOUND("Receipt not found", 404),
    USER_NOT_FOUND("User not found", 404),
    PHOTO_NOT_FOUND("Photo not found", 404),
    INGREDIENT_NOT_FOUND("Ingredient not found", 404),
    DAY_PLAN_NOT_FOUND("Day plan not found", 404),
    STEP_NOT_FOUND("Step not found", 404),
    CATEGORY_NOT_FOUND("Category not found", 404),
    SHOPPING_LIST_NOT_FOUND("Shopping list not found", 404),
    SHOPPING_LIST_ITEM_NOT_FOUND("Shopping list item not found", 404),
    CANNOT_LOGIN_WITH_FACEBOOK("Cannot login with Facebook", 400),
    MISSING_USER_ID("Missing user id", 400),
    JWT_MISSING_TOKEN("Token is missing", 400),
    JWT_EXPIRED_TOKEN("Token is expired", 400),
    JWT_MALFORMED_TOKEN("Token is malformed", 400),
    JWT_GENERIC_ERROR("Unknown token error", 400),
    INGREDIENT_GROUP_NOT_FOUND("Ingredient group not found", 404),
    RECEIPT_ID_IS_NOT_ASSIGNED_TO_DAY_PLAN("Receipt id is not assigned to day play", 404),
    DAY_PLAN_FOT_DATE_ALREADY_EXISTS("Day plan for provided date already exists", 400),
    DAY_PLAN_PAST_DATE("Cannot create day plan for passed date", 400);
}