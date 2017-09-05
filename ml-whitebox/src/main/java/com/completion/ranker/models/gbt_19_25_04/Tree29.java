/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.completion.ranker.models.gbt_19_25_04;

public class Tree29 {
    public double calcTree(double... fs) {
        if (fs[78] <= 0.5) {
            if (fs[0] <= 0.5) {
                if (fs[53] <= -1138.5) {
                    if (fs[30] <= 0.5) {
                        if (fs[4] <= 6.5) {
                            if (fs[71] <= 0.5) {
                                if (fs[11] <= 0.5) {
                                    if (fs[2] <= 2.5) {
                                        return 0.343204692106;
                                    } else {
                                        return 0.220450584261;
                                    }
                                } else {
                                    if (fs[40] <= 0.5) {
                                        return 0.211728974007;
                                    } else {
                                        return 0.213054888545;
                                    }
                                }
                            } else {
                                if (fs[2] <= 1.5) {
                                    if (fs[33] <= 0.5) {
                                        return 0.20783752579;
                                    } else {
                                        return 0.0901001996738;
                                    }
                                } else {
                                    if (fs[2] <= 5.5) {
                                        return 0.209625671472;
                                    } else {
                                        return 0.146521671714;
                                    }
                                }
                            }
                        } else {
                            if (fs[76] <= 125.0) {
                                if (fs[2] <= 1.5) {
                                    if (fs[4] <= 9.5) {
                                        return 0.0616339030332;
                                    } else {
                                        return 0.42361816231;
                                    }
                                } else {
                                    if (fs[4] <= 7.5) {
                                        return 0.211567819036;
                                    } else {
                                        return 0.251177405334;
                                    }
                                }
                            } else {
                                if (fs[76] <= 250.0) {
                                    return -0.137974389522;
                                } else {
                                    if (fs[4] <= 7.5) {
                                        return 0.285897407799;
                                    } else {
                                        return 0.103849417633;
                                    }
                                }
                            }
                        }
                    } else {
                        return -0.076538774119;
                    }
                } else {
                    if (fs[4] <= 9.5) {
                        if (fs[30] <= 0.5) {
                            if (fs[98] <= 1.5) {
                                if (fs[12] <= 0.5) {
                                    if (fs[14] <= 0.5) {
                                        return 0.220786778911;
                                    } else {
                                        return 0.302038829178;
                                    }
                                } else {
                                    if (fs[52] <= 0.5) {
                                        return 0.255455783486;
                                    } else {
                                        return 0.130442940842;
                                    }
                                }
                            } else {
                                if (fs[11] <= 0.5) {
                                    return 0.164846214048;
                                } else {
                                    return 0.0162382502519;
                                }
                            }
                        } else {
                            return -0.163212010176;
                        }
                    } else {
                        if (fs[72] <= 9998.5) {
                            if (fs[2] <= 2.5) {
                                if (fs[70] <= -1.5) {
                                    if (fs[52] <= 0.5) {
                                        return 0.310982539412;
                                    } else {
                                        return 0.198494968323;
                                    }
                                } else {
                                    return 0.0354492312194;
                                }
                            } else {
                                if (fs[4] <= 12.5) {
                                    return 0.164613377686;
                                } else {
                                    return 0.410421458761;
                                }
                            }
                        } else {
                            return -0.258050221482;
                        }
                    }
                }
            } else {
                if (fs[98] <= 0.5) {
                    if (fs[34] <= 0.5) {
                        if (fs[4] <= 2.5) {
                            if (fs[52] <= 0.5) {
                                return -0.13672236442;
                            } else {
                                if (fs[40] <= 0.5) {
                                    if (fs[0] <= 3.5) {
                                        return 0.264042505963;
                                    } else {
                                        return 0.094139245882;
                                    }
                                } else {
                                    return -0.125947253378;
                                }
                            }
                        } else {
                            if (fs[0] <= 2.5) {
                                if (fs[52] <= 0.5) {
                                    if (fs[11] <= 0.5) {
                                        return 0.0564639401517;
                                    } else {
                                        return -0.041952876493;
                                    }
                                } else {
                                    if (fs[68] <= 1.5) {
                                        return 0.0423584245932;
                                    } else {
                                        return 0.264394889627;
                                    }
                                }
                            } else {
                                if (fs[0] <= 61.5) {
                                    if (fs[4] <= 11.5) {
                                        return -0.00634404222751;
                                    } else {
                                        return -0.0287703540419;
                                    }
                                } else {
                                    if (fs[11] <= 0.5) {
                                        return 0.47486767379;
                                    } else {
                                        return 0.0339811154638;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[0] <= 2.5) {
                            if (fs[52] <= 0.5) {
                                return 0.178959557303;
                            } else {
                                if (fs[53] <= -1138.0) {
                                    return 0.214764257283;
                                } else {
                                    return 0.455762443879;
                                }
                            }
                        } else {
                            return 0.651764786215;
                        }
                    }
                } else {
                    if (fs[81] <= 0.5) {
                        return 0.206978014862;
                    } else {
                        if (fs[60] <= 0.5) {
                            if (fs[0] <= 1.5) {
                                if (fs[4] <= 21.5) {
                                    if (fs[4] <= 20.5) {
                                        return -0.0295802580479;
                                    } else {
                                        return 0.0653851867751;
                                    }
                                } else {
                                    return -0.026503711323;
                                }
                            } else {
                                if (fs[71] <= 0.5) {
                                    if (fs[4] <= 14.5) {
                                        return 0.0291058151231;
                                    } else {
                                        return -0.0147065371312;
                                    }
                                } else {
                                    if (fs[0] <= 6.5) {
                                        return -0.0697077838964;
                                    } else {
                                        return -0.0340822973175;
                                    }
                                }
                            }
                        } else {
                            if (fs[0] <= 1.5) {
                                if (fs[4] <= 13.5) {
                                    if (fs[87] <= 0.5) {
                                        return 0.226552958687;
                                    } else {
                                        return -0.0154863688461;
                                    }
                                } else {
                                    return 0.188501753431;
                                }
                            } else {
                                if (fs[31] <= 0.5) {
                                    if (fs[0] <= 3.5) {
                                        return 0.115448646813;
                                    } else {
                                        return -0.00150384487089;
                                    }
                                } else {
                                    if (fs[53] <= -1137.5) {
                                        return -0.0649310645922;
                                    } else {
                                        return -0.0106325929818;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (fs[72] <= 9994.5) {
                if (fs[87] <= 0.5) {
                    if (fs[76] <= 25.0) {
                        if (fs[0] <= 0.5) {
                            if (fs[25] <= 0.5) {
                                if (fs[2] <= 2.5) {
                                    if (fs[99] <= 0.5) {
                                        return 0.0346108350846;
                                    } else {
                                        return 0.144930965411;
                                    }
                                } else {
                                    if (fs[86] <= 0.5) {
                                        return 0.135109750678;
                                    } else {
                                        return 0.239433834384;
                                    }
                                }
                            } else {
                                if (fs[85] <= 3.5) {
                                    if (fs[98] <= 0.5) {
                                        return -0.0641124760355;
                                    } else {
                                        return 0.0407311608648;
                                    }
                                } else {
                                    if (fs[4] <= 15.5) {
                                        return 0.175669800174;
                                    } else {
                                        return -0.138599126226;
                                    }
                                }
                            }
                        } else {
                            if (fs[0] <= 3.5) {
                                if (fs[12] <= 0.5) {
                                    if (fs[18] <= 0.5) {
                                        return -0.0118430490741;
                                    } else {
                                        return 0.00684958836734;
                                    }
                                } else {
                                    if (fs[103] <= 0.5) {
                                        return 0.0131810086818;
                                    } else {
                                        return 0.277752459321;
                                    }
                                }
                            } else {
                                if (fs[0] <= 11.5) {
                                    if (fs[18] <= 0.5) {
                                        return -0.0117331011931;
                                    } else {
                                        return -0.00239032717717;
                                    }
                                } else {
                                    if (fs[82] <= 0.5) {
                                        return -0.0113920583168;
                                    } else {
                                        return -0.0124366471962;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[0] <= 0.5) {
                            if (fs[2] <= 1.5) {
                                if (fs[53] <= -1514.0) {
                                    if (fs[85] <= 5.0) {
                                        return 0.373571715943;
                                    } else {
                                        return 0.233886301583;
                                    }
                                } else {
                                    if (fs[4] <= 8.5) {
                                        return 0.0774829706302;
                                    } else {
                                        return -0.0481042171911;
                                    }
                                }
                            } else {
                                if (fs[25] <= 0.5) {
                                    if (fs[96] <= 0.5) {
                                        return 0.282719307365;
                                    } else {
                                        return 0.142274483994;
                                    }
                                } else {
                                    if (fs[4] <= 13.5) {
                                        return 0.167503812498;
                                    } else {
                                        return 0.032013194516;
                                    }
                                }
                            }
                        } else {
                            if (fs[0] <= 1.5) {
                                if (fs[11] <= 0.5) {
                                    if (fs[2] <= 2.5) {
                                        return 0.0430506238077;
                                    } else {
                                        return 0.161722884768;
                                    }
                                } else {
                                    if (fs[52] <= 0.5) {
                                        return -0.0134155328633;
                                    } else {
                                        return 0.0482806478206;
                                    }
                                }
                            } else {
                                if (fs[53] <= -1428.0) {
                                    if (fs[4] <= 6.5) {
                                        return 0.0435923190548;
                                    } else {
                                        return -0.00640300571571;
                                    }
                                } else {
                                    if (fs[37] <= 0.5) {
                                        return -0.0146889233703;
                                    } else {
                                        return -0.00460657524279;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[11] <= 0.5) {
                        if (fs[0] <= 0.5) {
                            if (fs[53] <= -21.0) {
                                if (fs[25] <= 0.5) {
                                    if (fs[40] <= 0.5) {
                                        return 0.21023204892;
                                    } else {
                                        return -0.0198482843578;
                                    }
                                } else {
                                    if (fs[76] <= 75.0) {
                                        return 0.332491202963;
                                    } else {
                                        return 0.254849706169;
                                    }
                                }
                            } else {
                                if (fs[64] <= -496.0) {
                                    if (fs[94] <= 0.5) {
                                        return 0.351716560691;
                                    } else {
                                        return 0.172547514348;
                                    }
                                } else {
                                    if (fs[81] <= 0.5) {
                                        return 0.284800045961;
                                    } else {
                                        return 0.0824819501886;
                                    }
                                }
                            }
                        } else {
                            if (fs[53] <= -1052.5) {
                                if (fs[81] <= 0.5) {
                                    if (fs[76] <= 75.0) {
                                        return 0.290227022371;
                                    } else {
                                        return 0.174054788237;
                                    }
                                } else {
                                    if (fs[0] <= 1.5) {
                                        return 0.0949578988252;
                                    } else {
                                        return 0.021972787466;
                                    }
                                }
                            } else {
                                if (fs[76] <= 250.0) {
                                    if (fs[36] <= 0.5) {
                                        return 0.00924968463134;
                                    } else {
                                        return 0.383630932156;
                                    }
                                } else {
                                    if (fs[28] <= 0.5) {
                                        return -0.0139662111638;
                                    } else {
                                        return 0.0741072177448;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[0] <= 0.5) {
                            if (fs[2] <= 1.5) {
                                if (fs[80] <= 0.5) {
                                    if (fs[53] <= -1493.5) {
                                        return 0.309987974233;
                                    } else {
                                        return 0.128371209491;
                                    }
                                } else {
                                    if (fs[76] <= 250.0) {
                                        return -0.28738209114;
                                    } else {
                                        return -0.0471775004372;
                                    }
                                }
                            } else {
                                if (fs[40] <= 0.5) {
                                    if (fs[43] <= 0.5) {
                                        return 0.206496448829;
                                    } else {
                                        return -0.0160042140554;
                                    }
                                } else {
                                    if (fs[76] <= 150.0) {
                                        return 0.076347590381;
                                    } else {
                                        return 0.0114288762521;
                                    }
                                }
                            }
                        } else {
                            if (fs[53] <= -1418.0) {
                                if (fs[40] <= 0.5) {
                                    if (fs[52] <= 0.5) {
                                        return -0.00755918028521;
                                    } else {
                                        return 0.0453025737448;
                                    }
                                } else {
                                    if (fs[72] <= 4327.0) {
                                        return 0.148455541898;
                                    } else {
                                        return -0.0203671363246;
                                    }
                                }
                            } else {
                                if (fs[0] <= 1.5) {
                                    if (fs[33] <= 0.5) {
                                        return 0.0356595400341;
                                    } else {
                                        return 0.00941242201546;
                                    }
                                } else {
                                    if (fs[52] <= 0.5) {
                                        return -0.0151172284531;
                                    } else {
                                        return -0.00411008096197;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (fs[47] <= 0.5) {
                    if (fs[0] <= 0.5) {
                        if (fs[98] <= 1.5) {
                            if (fs[80] <= 0.5) {
                                if (fs[4] <= 12.5) {
                                    if (fs[33] <= 0.5) {
                                        return 0.216877294546;
                                    } else {
                                        return 0.163045311401;
                                    }
                                } else {
                                    if (fs[85] <= 6.5) {
                                        return 0.0879683549908;
                                    } else {
                                        return 0.246074024451;
                                    }
                                }
                            } else {
                                if (fs[76] <= 75.0) {
                                    return 0.118037023265;
                                } else {
                                    if (fs[102] <= 0.5) {
                                        return -0.399339972969;
                                    } else {
                                        return 0.0199898099386;
                                    }
                                }
                            }
                        } else {
                            if (fs[81] <= 0.5) {
                                if (fs[42] <= 0.5) {
                                    if (fs[2] <= 6.5) {
                                        return 0.16849926073;
                                    } else {
                                        return 0.248246359331;
                                    }
                                } else {
                                    if (fs[76] <= 150.0) {
                                        return -0.0713182333407;
                                    } else {
                                        return 0.195572583231;
                                    }
                                }
                            } else {
                                if (fs[53] <= -451.5) {
                                    if (fs[4] <= 17.5) {
                                        return 0.168928660792;
                                    } else {
                                        return -0.0350777168581;
                                    }
                                } else {
                                    if (fs[64] <= -992.5) {
                                        return 0.283610823856;
                                    } else {
                                        return 0.00446954543466;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[53] <= -1418.0) {
                            if (fs[4] <= 12.5) {
                                if (fs[76] <= 25.0) {
                                    if (fs[82] <= 0.5) {
                                        return -0.029166019412;
                                    } else {
                                        return 0.0774781567719;
                                    }
                                } else {
                                    if (fs[72] <= 9999.5) {
                                        return 0.109968048179;
                                    } else {
                                        return 0.197867899553;
                                    }
                                }
                            } else {
                                if (fs[66] <= 5.0) {
                                    if (fs[4] <= 26.5) {
                                        return 0.060554155702;
                                    } else {
                                        return -0.0229405760528;
                                    }
                                } else {
                                    return -0.2626985585;
                                }
                            }
                        } else {
                            if (fs[4] <= 4.5) {
                                if (fs[2] <= 1.5) {
                                    if (fs[72] <= 9998.5) {
                                        return 0.0405432893604;
                                    } else {
                                        return 0.0948443826367;
                                    }
                                } else {
                                    if (fs[68] <= 1.5) {
                                        return 0.0800407752344;
                                    } else {
                                        return 0.404369627759;
                                    }
                                }
                            } else {
                                if (fs[12] <= 0.5) {
                                    if (fs[25] <= 0.5) {
                                        return 0.0205659628228;
                                    } else {
                                        return -0.0442072459479;
                                    }
                                } else {
                                    if (fs[40] <= 0.5) {
                                        return 0.0625332024528;
                                    } else {
                                        return 0.21442910844;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[0] <= 0.5) {
                        if (fs[4] <= 19.5) {
                            return 0.266186354098;
                        } else {
                            return -0.250070874401;
                        }
                    } else {
                        if (fs[101] <= 0.5) {
                            if (fs[14] <= 0.5) {
                                if (fs[76] <= 75.0) {
                                    if (fs[85] <= 2.5) {
                                        return -0.0266283757018;
                                    } else {
                                        return -0.0393391051769;
                                    }
                                } else {
                                    if (fs[37] <= 0.5) {
                                        return -0.0137318404543;
                                    } else {
                                        return -0.0657000810402;
                                    }
                                }
                            } else {
                                if (fs[60] <= 0.5) {
                                    return 0.184278500282;
                                } else {
                                    if (fs[72] <= 9999.5) {
                                        return -0.016520464003;
                                    } else {
                                        return -0.0260958036613;
                                    }
                                }
                            }
                        } else {
                            if (fs[59] <= 0.5) {
                                if (fs[53] <= -491.5) {
                                    return -0.0378640748623;
                                } else {
                                    return -0.0284980231605;
                                }
                            } else {
                                if (fs[0] <= 1.5) {
                                    if (fs[2] <= 2.5) {
                                        return -0.080231998809;
                                    } else {
                                        return -0.0871520294113;
                                    }
                                } else {
                                    return -0.0489355919277;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
