package com.icodeu.bakeryapp.utils

object HttpErrorCode {
    val httpErrorMap: HashMap<Int, String> = hashMapOf(
        Pair(200, "Generic everything is OK"),
        Pair(201, "Created something OK"),
        Pair(
            202,
            "Accepted but is being processed async (video is encoding, image is resizing, etc)"
        ),
        Pair(400, "Wrong arguments (missing validation)"),
        Pair(401, "Unauthorized (no current user and there should be)"),
        Pair(403, "The current user is forbidden from accessing this data"),
        Pair(404, "That URL is not a valid route, or the item resource does not exist"),
        Pair(410, "Data has been deleted, deactivated, suspended, etc"),
        Pair(405, "Method Not Allowed (your framework will probably do this for you)"),
        Pair(500, "Something unexpected happened and it is the APIs fault"),
        Pair(503, "API is not here right now, please try again later"),
    )
   fun getErrorMessage(code:Int):String{
      if (httpErrorMap[code] != null){
         return httpErrorMap[code]!!
      }
      return "Unexpected Error"
   }
}