/*
 * Copyright (c) 2017 Smart Points Ltd. - All rights reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Created by danie on 21/12/17 10:03
 */

package com.amplify.utils.location


data class CommonLocation(internal val latitude: Double = 0.0,
                          internal val longitude: Double = 0.0,
                          internal val accuracy: Float = 0f,
                          internal var country: String = "0")