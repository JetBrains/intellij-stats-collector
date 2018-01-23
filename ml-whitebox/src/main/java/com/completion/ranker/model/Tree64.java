/*
 * Copyright 2000-2018 JetBrains s.r.o.
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

package com.completion.ranker.model;

public class Tree64 {
    public double calcTree(double... fs) {
        if (fs[31] <= 0.5) {
            if (fs[78] <= 0.5) {
                if (fs[76] <= 25.0) {
                    if (fs[0] <= 0.5) {
                        if (fs[30] <= 0.5) {
                            if (fs[53] <= -1138.5) {
                                if (fs[2] <= 1.5) {
                                    if (fs[4] <= 4.5) {
                                        return 0.046323196825;
                                    } else {
                                        return -0.0137819778808;
                                    }
                                } else {
                                    if (fs[4] <= 7.5) {
                                        return 0.0363728590122;
                                    } else {
                                        return 0.0693290055316;
                                    }
                                }
                            } else {
                                if (fs[14] <= 0.5) {
                                    if (fs[53] <= -968.0) {
                                        return 0.0491364459801;
                                    } else {
                                        return 0.0252490492517;
                                    }
                                } else {
                                    if (fs[4] <= 5.5) {
                                        return 0.109534137074;
                                    } else {
                                        return 0.13341165136;
                                    }
                                }
                            }
                        } else {
                            if (fs[4] <= 3.5) {
                                return 0.098754247931;
                            } else {
                                if (fs[4] <= 6.5) {
                                    return -0.324378718024;
                                } else {
                                    return -0.155428943978;
                                }
                            }
                        }
                    } else {
                        if (fs[52] <= 0.5) {
                            if (fs[15] <= 0.5) {
                                if (fs[11] <= 0.5) {
                                    if (fs[71] <= 0.5) {
                                        return 0.152952400437;
                                    } else {
                                        return -0.0326230119909;
                                    }
                                } else {
                                    if (fs[70] <= -1.5) {
                                        return -0.0247743324928;
                                    } else {
                                        return 0.0391346690615;
                                    }
                                }
                            } else {
                                if (fs[2] <= 1.5) {
                                    if (fs[0] <= 53.5) {
                                        return -0.00199745805729;
                                    } else {
                                        return 0.156739088074;
                                    }
                                } else {
                                    if (fs[4] <= 3.5) {
                                        return 0.0620682670248;
                                    } else {
                                        return 0.283193564271;
                                    }
                                }
                            }
                        } else {
                            if (fs[53] <= -1108.0) {
                                if (fs[4] <= 7.5) {
                                    if (fs[71] <= 0.5) {
                                        return 0.0729187168099;
                                    } else {
                                        return 0.0242589019074;
                                    }
                                } else {
                                    if (fs[11] <= 0.5) {
                                        return 0.1308694439;
                                    } else {
                                        return -0.0218818562458;
                                    }
                                }
                            } else {
                                if (fs[0] <= 2.5) {
                                    if (fs[4] <= 4.5) {
                                        return 0.0446175823026;
                                    } else {
                                        return -0.0574508926212;
                                    }
                                } else {
                                    if (fs[72] <= 9825.5) {
                                        return -0.0109009083206;
                                    } else {
                                        return -0.0640674194539;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[94] <= 0.5) {
                        if (fs[0] <= 1.5) {
                            if (fs[47] <= 0.5) {
                                if (fs[4] <= 18.5) {
                                    return 0.0256693413879;
                                } else {
                                    if (fs[0] <= 0.5) {
                                        return -0.118633810616;
                                    } else {
                                        return -0.0154662593476;
                                    }
                                }
                            } else {
                                return 0.00962980359171;
                            }
                        } else {
                            if (fs[0] <= 3.5) {
                                if (fs[0] <= 2.5) {
                                    if (fs[2] <= 1.5) {
                                        return 0.00483653481233;
                                    } else {
                                        return -0.00901578751339;
                                    }
                                } else {
                                    if (fs[53] <= -978.0) {
                                        return -0.00631936868015;
                                    } else {
                                        return -0.0050021196018;
                                    }
                                }
                            } else {
                                if (fs[4] <= 33.5) {
                                    if (fs[4] <= 16.5) {
                                        return -0.00837712169384;
                                    } else {
                                        return -0.00294728788525;
                                    }
                                } else {
                                    if (fs[4] <= 43.5) {
                                        return 0.000353399360571;
                                    } else {
                                        return -0.00335693060929;
                                    }
                                }
                            }
                        }
                    } else {
                        return 0.120362010753;
                    }
                }
            } else {
                if (fs[12] <= 0.5) {
                    if (fs[0] <= 0.5) {
                        if (fs[55] <= 0.5) {
                            if (fs[2] <= 4.5) {
                                if (fs[25] <= 0.5) {
                                    if (fs[53] <= -882.0) {
                                        return 0.0364199536289;
                                    } else {
                                        return -0.00419848703359;
                                    }
                                } else {
                                    if (fs[53] <= -1057.5) {
                                        return -0.00511471232857;
                                    } else {
                                        return -0.114683287768;
                                    }
                                }
                            } else {
                                if (fs[40] <= 0.5) {
                                    if (fs[25] <= 0.5) {
                                        return 0.0773491071532;
                                    } else {
                                        return 0.0478840192689;
                                    }
                                } else {
                                    if (fs[52] <= 0.5) {
                                        return -0.104466221355;
                                    } else {
                                        return 0.00655162871707;
                                    }
                                }
                            }
                        } else {
                            if (fs[2] <= 1.5) {
                                if (fs[85] <= 3.0) {
                                    if (fs[52] <= 0.5) {
                                        return 0.233304554752;
                                    } else {
                                        return 0.301588770505;
                                    }
                                } else {
                                    return 0.385224276052;
                                }
                            } else {
                                if (fs[51] <= 0.5) {
                                    if (fs[4] <= 9.5) {
                                        return 0.161433184994;
                                    } else {
                                        return 0.291939049947;
                                    }
                                } else {
                                    return -0.333604588401;
                                }
                            }
                        }
                    } else {
                        if (fs[4] <= 14.5) {
                            if (fs[53] <= -1478.5) {
                                if (fs[76] <= 75.0) {
                                    if (fs[72] <= 9688.5) {
                                        return -0.0020823577632;
                                    } else {
                                        return 0.0140354389819;
                                    }
                                } else {
                                    if (fs[85] <= 5.5) {
                                        return 0.0469334461808;
                                    } else {
                                        return -0.00176285248898;
                                    }
                                }
                            } else {
                                if (fs[55] <= -1.5) {
                                    if (fs[72] <= 9579.5) {
                                        return 0.0469158362818;
                                    } else {
                                        return 0.404397376128;
                                    }
                                } else {
                                    if (fs[55] <= 548.5) {
                                        return -0.00192532502295;
                                    } else {
                                        return 0.37326048075;
                                    }
                                }
                            }
                        } else {
                            if (fs[76] <= 150.0) {
                                if (fs[14] <= 0.5) {
                                    if (fs[55] <= -0.5) {
                                        return -0.0207886477396;
                                    } else {
                                        return -0.00248692202729;
                                    }
                                } else {
                                    if (fs[72] <= 9989.5) {
                                        return 0.00428300265389;
                                    } else {
                                        return 0.140660157979;
                                    }
                                }
                            } else {
                                if (fs[53] <= -1137.5) {
                                    if (fs[50] <= 0.5) {
                                        return -0.0148060897128;
                                    } else {
                                        return 0.131396758794;
                                    }
                                } else {
                                    if (fs[14] <= 0.5) {
                                        return -0.00343373331465;
                                    } else {
                                        return 0.0337067371132;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[0] <= 0.5) {
                        if (fs[100] <= 1.5) {
                            if (fs[59] <= 0.5) {
                                if (fs[64] <= -997.5) {
                                    if (fs[53] <= -1453.0) {
                                        return -0.058899148826;
                                    } else {
                                        return 0.116196573528;
                                    }
                                } else {
                                    if (fs[57] <= 0.5) {
                                        return 0.0248696534798;
                                    } else {
                                        return 0.181894616677;
                                    }
                                }
                            } else {
                                if (fs[43] <= 0.5) {
                                    if (fs[4] <= 17.0) {
                                        return 0.00401358783159;
                                    } else {
                                        return -0.212677654417;
                                    }
                                } else {
                                    if (fs[4] <= 10.5) {
                                        return 0.0833600398553;
                                    } else {
                                        return 0.133316754382;
                                    }
                                }
                            }
                        } else {
                            if (fs[58] <= 0.5) {
                                if (fs[4] <= 11.5) {
                                    return -0.151546510333;
                                } else {
                                    return -0.415077980796;
                                }
                            } else {
                                if (fs[4] <= 9.0) {
                                    return -0.492202098383;
                                } else {
                                    return -0.0829897406235;
                                }
                            }
                        }
                    } else {
                        if (fs[0] <= 6.5) {
                            if (fs[47] <= 0.5) {
                                if (fs[70] <= -1.5) {
                                    if (fs[96] <= 0.5) {
                                        return 0.00556198079223;
                                    } else {
                                        return 0.0238394609441;
                                    }
                                } else {
                                    return 0.548114624559;
                                }
                            } else {
                                if (fs[4] <= 38.5) {
                                    if (fs[68] <= 1.5) {
                                        return -0.0139756356727;
                                    } else {
                                        return 0.102180907281;
                                    }
                                } else {
                                    if (fs[96] <= 0.5) {
                                        return 0.278528525341;
                                    } else {
                                        return -0.0294676487161;
                                    }
                                }
                            }
                        } else {
                            if (fs[0] <= 16.5) {
                                if (fs[71] <= 0.5) {
                                    if (fs[2] <= 8.5) {
                                        return 0.00319086176176;
                                    } else {
                                        return 0.0835687896619;
                                    }
                                } else {
                                    if (fs[62] <= -1.5) {
                                        return 0.0675507564958;
                                    } else {
                                        return -0.00092051905753;
                                    }
                                }
                            } else {
                                if (fs[62] <= -0.5) {
                                    if (fs[0] <= 33.5) {
                                        return -0.00158357255426;
                                    } else {
                                        return 0.16636983654;
                                    }
                                } else {
                                    if (fs[18] <= 0.5) {
                                        return -0.00351586326324;
                                    } else {
                                        return -0.00599205580142;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (fs[47] <= 0.5) {
                if (fs[4] <= 3.5) {
                    if (fs[4] <= 1.5) {
                        if (fs[87] <= 0.5) {
                            return 0.0628069379676;
                        } else {
                            if (fs[52] <= 0.5) {
                                if (fs[96] <= 0.5) {
                                    return -0.188205409157;
                                } else {
                                    if (fs[64] <= -499.5) {
                                        return -0.0225550197775;
                                    } else {
                                        return 0.0500749590933;
                                    }
                                }
                            } else {
                                if (fs[12] <= 0.5) {
                                    return -0.0684181353067;
                                } else {
                                    return 0.143833485818;
                                }
                            }
                        }
                    } else {
                        if (fs[14] <= 0.5) {
                            if (fs[62] <= -0.5) {
                                if (fs[49] <= -0.5) {
                                    if (fs[11] <= 0.5) {
                                        return 0.0558602368033;
                                    } else {
                                        return 0.0121348001061;
                                    }
                                } else {
                                    return -0.113637204534;
                                }
                            } else {
                                if (fs[40] <= 0.5) {
                                    if (fs[12] <= 0.5) {
                                        return 0.073415313426;
                                    } else {
                                        return 0.0524691322937;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return -0.0550012896831;
                                    } else {
                                        return -0.143962751813;
                                    }
                                }
                            }
                        } else {
                            if (fs[53] <= -1133.5) {
                                return 0.102326661302;
                            } else {
                                return 0.0909057020095;
                            }
                        }
                    }
                } else {
                    if (fs[0] <= 0.5) {
                        if (fs[77] <= 0.5) {
                            if (fs[52] <= 0.5) {
                                if (fs[4] <= 19.5) {
                                    if (fs[58] <= 0.5) {
                                        return 0.03619453486;
                                    } else {
                                        return -0.0561851842514;
                                    }
                                } else {
                                    if (fs[12] <= 0.5) {
                                        return 0.162704456095;
                                    } else {
                                        return 0.0774508146443;
                                    }
                                }
                            } else {
                                if (fs[2] <= 6.5) {
                                    if (fs[4] <= 26.5) {
                                        return 0.0125350149773;
                                    } else {
                                        return -0.492033662589;
                                    }
                                } else {
                                    if (fs[4] <= 16.5) {
                                        return 0.0590798683919;
                                    } else {
                                        return 0.170183825036;
                                    }
                                }
                            }
                        } else {
                            if (fs[4] <= 6.5) {
                                return 0.0868409711165;
                            } else {
                                return 0.182031466772;
                            }
                        }
                    } else {
                        if (fs[52] <= 0.5) {
                            if (fs[14] <= 0.5) {
                                if (fs[53] <= -1133.5) {
                                    if (fs[64] <= -997.5) {
                                        return 0.0517313115211;
                                    } else {
                                        return -0.027945503518;
                                    }
                                } else {
                                    if (fs[53] <= -988.0) {
                                        return 0.0216845531401;
                                    } else {
                                        return -0.0409845280788;
                                    }
                                }
                            } else {
                                if (fs[53] <= -1138.0) {
                                    if (fs[64] <= -499.0) {
                                        return 0.0972179818259;
                                    } else {
                                        return 0.186071790212;
                                    }
                                } else {
                                    return 0.0114861525207;
                                }
                            }
                        } else {
                            if (fs[76] <= 250.0) {
                                if (fs[0] <= 2.5) {
                                    if (fs[4] <= 13.5) {
                                        return 0.0989584253077;
                                    } else {
                                        return -0.0492585429378;
                                    }
                                } else {
                                    if (fs[4] <= 8.5) {
                                        return 0.10559688266;
                                    } else {
                                        return -0.00100966957681;
                                    }
                                }
                            } else {
                                if (fs[53] <= -1137.5) {
                                    if (fs[0] <= 16.0) {
                                        return 0.0387706292762;
                                    } else {
                                        return 0.246330657934;
                                    }
                                } else {
                                    if (fs[53] <= -1118.0) {
                                        return 0.121231012029;
                                    } else {
                                        return -0.00376535459783;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (fs[16] <= 0.5) {
                    if (fs[0] <= 3.5) {
                        if (fs[12] <= 0.5) {
                            if (fs[94] <= 0.5) {
                                if (fs[0] <= 1.5) {
                                    if (fs[53] <= -947.5) {
                                        return -0.089691277004;
                                    } else {
                                        return -0.0147068229494;
                                    }
                                } else {
                                    if (fs[81] <= 0.5) {
                                        return -0.00354354387109;
                                    } else {
                                        return -0.0142006920835;
                                    }
                                }
                            } else {
                                if (fs[0] <= 1.5) {
                                    if (fs[2] <= 1.5) {
                                        return -0.00387496573477;
                                    } else {
                                        return -0.0208308174405;
                                    }
                                } else {
                                    if (fs[2] <= 3.5) {
                                        return -0.00447235556249;
                                    } else {
                                        return 0.00249714873781;
                                    }
                                }
                            }
                        } else {
                            if (fs[49] <= -1.5) {
                                return 0.0590964090128;
                            } else {
                                if (fs[87] <= 0.5) {
                                    return 0.0501922601025;
                                } else {
                                    if (fs[49] <= -0.5) {
                                        return 0.0160316659145;
                                    } else {
                                        return -0.00196614138798;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[49] <= -0.5) {
                            if (fs[11] <= 0.5) {
                                if (fs[53] <= -487.0) {
                                    return 0.0117747996527;
                                } else {
                                    if (fs[62] <= -1.5) {
                                        return -0.031895451328;
                                    } else {
                                        return -0.0105985056313;
                                    }
                                }
                            } else {
                                if (fs[49] <= -1.5) {
                                    return -0.0140674352165;
                                } else {
                                    if (fs[0] <= 6.5) {
                                        return -0.00226642735244;
                                    } else {
                                        return -0.00865817934896;
                                    }
                                }
                            }
                        } else {
                            if (fs[102] <= 0.5) {
                                if (fs[94] <= 0.5) {
                                    if (fs[52] <= 0.5) {
                                        return -0.00218070133946;
                                    } else {
                                        return -0.00752116263212;
                                    }
                                } else {
                                    if (fs[4] <= 9.5) {
                                        return -0.00218012350503;
                                    } else {
                                        return -0.00100329863733;
                                    }
                                }
                            } else {
                                if (fs[53] <= -947.5) {
                                    if (fs[85] <= 3.5) {
                                        return -0.00536690674154;
                                    } else {
                                        return -0.00271684499776;
                                    }
                                } else {
                                    if (fs[0] <= 12.5) {
                                        return 0.0780984280139;
                                    } else {
                                        return -0.00427679157755;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    return 0.0279180156537;
                }
            }
        }
    }
}
