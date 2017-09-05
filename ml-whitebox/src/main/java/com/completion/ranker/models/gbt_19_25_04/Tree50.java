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

public class Tree50 {
    public double calcTree(double... fs) {
        if (fs[0] <= 0.5) {
            if (fs[42] <= 0.5) {
                if (fs[2] <= 1.5) {
                    if (fs[64] <= -496.0) {
                        if (fs[72] <= 4963.0) {
                            if (fs[70] <= -4.0) {
                                if (fs[98] <= 0.5) {
                                    if (fs[4] <= 9.5) {
                                        return -0.165907296214;
                                    } else {
                                        return -0.0891970384807;
                                    }
                                } else {
                                    if (fs[4] <= 8.5) {
                                        return 0.162534099353;
                                    } else {
                                        return -0.104754264357;
                                    }
                                }
                            } else {
                                if (fs[18] <= 0.5) {
                                    if (fs[98] <= 1.5) {
                                        return -0.0509759616474;
                                    } else {
                                        return 0.0893171309084;
                                    }
                                } else {
                                    if (fs[64] <= -996.5) {
                                        return 0.159527813285;
                                    } else {
                                        return 0.0726913665442;
                                    }
                                }
                            }
                        } else {
                            if (fs[87] <= 0.5) {
                                if (fs[64] <= -997.5) {
                                    if (fs[64] <= -998.5) {
                                        return 0.0947391000043;
                                    } else {
                                        return 0.276256833647;
                                    }
                                } else {
                                    if (fs[4] <= 13.5) {
                                        return 0.243468304808;
                                    } else {
                                        return 0.122008829307;
                                    }
                                }
                            } else {
                                if (fs[4] <= 13.5) {
                                    if (fs[64] <= -998.5) {
                                        return 0.24013543563;
                                    } else {
                                        return 0.135316230249;
                                    }
                                } else {
                                    return -0.0564242544384;
                                }
                            }
                        }
                    } else {
                        if (fs[11] <= 0.5) {
                            if (fs[4] <= 5.5) {
                                if (fs[98] <= 0.5) {
                                    if (fs[74] <= 0.5) {
                                        return 0.0756387879913;
                                    } else {
                                        return 0.00797973344012;
                                    }
                                } else {
                                    if (fs[18] <= 0.5) {
                                        return 0.0774436066461;
                                    } else {
                                        return 0.172483051122;
                                    }
                                }
                            } else {
                                if (fs[43] <= 0.5) {
                                    if (fs[98] <= 0.5) {
                                        return -0.0268579098256;
                                    } else {
                                        return 0.0374734211469;
                                    }
                                } else {
                                    if (fs[7] <= 0.5) {
                                        return 0.144500377179;
                                    } else {
                                        return 0.0645434918832;
                                    }
                                }
                            }
                        } else {
                            if (fs[53] <= -1498.5) {
                                if (fs[2] <= 0.5) {
                                    return -0.154113396896;
                                } else {
                                    if (fs[4] <= 15.5) {
                                        return 0.223853321856;
                                    } else {
                                        return 0.131786745326;
                                    }
                                }
                            } else {
                                if (fs[53] <= -1488.5) {
                                    if (fs[72] <= 9998.5) {
                                        return -0.0822223525177;
                                    } else {
                                        return 0.0547535291671;
                                    }
                                } else {
                                    if (fs[53] <= -1053.5) {
                                        return 0.042743371457;
                                    } else {
                                        return -0.0388015389941;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[4] <= 18.5) {
                        if (fs[85] <= -0.5) {
                            if (fs[53] <= -1478.5) {
                                if (fs[59] <= 0.5) {
                                    if (fs[53] <= -1763.0) {
                                        return -0.197357722959;
                                    } else {
                                        return -0.0443966741089;
                                    }
                                } else {
                                    if (fs[2] <= 2.5) {
                                        return -0.0947869157459;
                                    } else {
                                        return 0.0833085108407;
                                    }
                                }
                            } else {
                                if (fs[12] <= 0.5) {
                                    if (fs[4] <= 8.5) {
                                        return -0.184328974394;
                                    } else {
                                        return -0.075808878904;
                                    }
                                } else {
                                    return -0.021006317838;
                                }
                            }
                        } else {
                            if (fs[2] <= 5.5) {
                                if (fs[4] <= 8.5) {
                                    if (fs[74] <= 0.5) {
                                        return 0.0778285245247;
                                    } else {
                                        return -0.0105269846673;
                                    }
                                } else {
                                    if (fs[68] <= 1.5) {
                                        return 0.0428835168505;
                                    } else {
                                        return -0.158423075796;
                                    }
                                }
                            } else {
                                if (fs[52] <= 0.5) {
                                    if (fs[33] <= 0.5) {
                                        return 0.0390838814253;
                                    } else {
                                        return 0.122237315603;
                                    }
                                } else {
                                    if (fs[78] <= 0.5) {
                                        return 0.0773151064343;
                                    } else {
                                        return 0.129899112294;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[12] <= 0.5) {
                            if (fs[25] <= 0.5) {
                                if (fs[64] <= -997.5) {
                                    return -0.197075600616;
                                } else {
                                    if (fs[4] <= 19.5) {
                                        return 0.108920100223;
                                    } else {
                                        return 0.00523816091625;
                                    }
                                }
                            } else {
                                if (fs[2] <= 8.5) {
                                    if (fs[53] <= -1608.0) {
                                        return 0.105065351219;
                                    } else {
                                        return -0.0778931417745;
                                    }
                                } else {
                                    if (fs[4] <= 26.5) {
                                        return 0.131073338894;
                                    } else {
                                        return -0.0673887616236;
                                    }
                                }
                            }
                        } else {
                            if (fs[25] <= 0.5) {
                                if (fs[70] <= -3.5) {
                                    if (fs[4] <= 19.5) {
                                        return 0.142298007294;
                                    } else {
                                        return -0.158102146933;
                                    }
                                } else {
                                    if (fs[72] <= 9999.5) {
                                        return 0.0770758827719;
                                    } else {
                                        return -0.0167947877835;
                                    }
                                }
                            } else {
                                if (fs[102] <= 0.5) {
                                    if (fs[2] <= 7.5) {
                                        return 0.155157371617;
                                    } else {
                                        return 0.345104554848;
                                    }
                                } else {
                                    if (fs[76] <= 25.0) {
                                        return -0.166905608815;
                                    } else {
                                        return 0.0648599265426;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (fs[4] <= 6.5) {
                    if (fs[82] <= 0.5) {
                        if (fs[72] <= 9851.5) {
                            return -0.0642033091121;
                        } else {
                            return -0.237610975292;
                        }
                    } else {
                        if (fs[4] <= 3.5) {
                            return -0.146941757429;
                        } else {
                            if (fs[11] <= 0.5) {
                                return 0.229022932214;
                            } else {
                                if (fs[53] <= -1488.0) {
                                    if (fs[76] <= 150.0) {
                                        return 0.0325016323722;
                                    } else {
                                        return 0.170310854961;
                                    }
                                } else {
                                    if (fs[94] <= 0.5) {
                                        return -0.034117301355;
                                    } else {
                                        return 0.148518711813;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[53] <= -1633.0) {
                        if (fs[72] <= 3267.0) {
                            if (fs[76] <= 25.0) {
                                return 0.228910399178;
                            } else {
                                return 0.457158656301;
                            }
                        } else {
                            if (fs[60] <= 0.5) {
                                return -0.21095905272;
                            } else {
                                return 0.169306859776;
                            }
                        }
                    } else {
                        if (fs[72] <= 2989.5) {
                            if (fs[52] <= 0.5) {
                                if (fs[4] <= 10.5) {
                                    return -0.157223668656;
                                } else {
                                    if (fs[2] <= 2.5) {
                                        return -0.176045312952;
                                    } else {
                                        return -0.332795118978;
                                    }
                                }
                            } else {
                                if (fs[2] <= 4.5) {
                                    if (fs[4] <= 11.5) {
                                        return -0.0396721642973;
                                    } else {
                                        return 0.0731986118482;
                                    }
                                } else {
                                    if (fs[53] <= -1468.0) {
                                        return 0.15614071327;
                                    } else {
                                        return -0.0054624449412;
                                    }
                                }
                            }
                        } else {
                            if (fs[98] <= 1.5) {
                                if (fs[76] <= 25.0) {
                                    if (fs[53] <= -1488.0) {
                                        return 0.0349320789976;
                                    } else {
                                        return -0.128346082966;
                                    }
                                } else {
                                    if (fs[53] <= -1453.0) {
                                        return -0.215008200348;
                                    } else {
                                        return 0.0466561838758;
                                    }
                                }
                            } else {
                                if (fs[72] <= 9838.5) {
                                    if (fs[4] <= 9.0) {
                                        return -0.198008753455;
                                    } else {
                                        return -0.288735007355;
                                    }
                                } else {
                                    if (fs[11] <= 0.5) {
                                        return 0.142703600237;
                                    } else {
                                        return -0.072246075749;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (fs[55] <= 998.5) {
                if (fs[0] <= 1.5) {
                    if (fs[47] <= 0.5) {
                        if (fs[76] <= 25.0) {
                            if (fs[102] <= 0.5) {
                                if (fs[14] <= 0.5) {
                                    if (fs[4] <= 4.5) {
                                        return 0.0342719657239;
                                    } else {
                                        return 0.00451116212113;
                                    }
                                } else {
                                    if (fs[87] <= 0.5) {
                                        return 0.0981742650804;
                                    } else {
                                        return 0.273448597715;
                                    }
                                }
                            } else {
                                if (fs[72] <= 9980.5) {
                                    if (fs[18] <= 0.5) {
                                        return -0.0131127696458;
                                    } else {
                                        return 0.00772028443029;
                                    }
                                } else {
                                    if (fs[85] <= -0.5) {
                                        return -0.183723051772;
                                    } else {
                                        return 0.0283799285208;
                                    }
                                }
                            }
                        } else {
                            if (fs[4] <= 8.5) {
                                if (fs[82] <= 0.5) {
                                    if (fs[60] <= 0.5) {
                                        return -0.0403023366517;
                                    } else {
                                        return 0.0315693561527;
                                    }
                                } else {
                                    if (fs[4] <= 5.5) {
                                        return 0.0138216928433;
                                    } else {
                                        return 0.0833967876264;
                                    }
                                }
                            } else {
                                if (fs[85] <= 5.5) {
                                    if (fs[11] <= 0.5) {
                                        return 0.0662346433596;
                                    } else {
                                        return 0.0116197197842;
                                    }
                                } else {
                                    if (fs[14] <= 0.5) {
                                        return -0.03105667936;
                                    } else {
                                        return 0.223317509335;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[40] <= 0.5) {
                            if (fs[81] <= 0.5) {
                                if (fs[12] <= 0.5) {
                                    if (fs[71] <= 0.5) {
                                        return -0.0211653849421;
                                    } else {
                                        return -0.0262138169075;
                                    }
                                } else {
                                    if (fs[76] <= 25.0) {
                                        return -0.0398339089189;
                                    } else {
                                        return -0.0587072839821;
                                    }
                                }
                            } else {
                                if (fs[68] <= 1.5) {
                                    if (fs[53] <= -1037.0) {
                                        return 0.123253480857;
                                    } else {
                                        return -0.017623153471;
                                    }
                                } else {
                                    if (fs[96] <= 0.5) {
                                        return 0.118582824741;
                                    } else {
                                        return 0.114968243769;
                                    }
                                }
                            }
                        } else {
                            if (fs[53] <= -982.0) {
                                return 0.173226842749;
                            } else {
                                if (fs[2] <= 2.5) {
                                    return -0.0334951645086;
                                } else {
                                    return -0.0552346849116;
                                }
                            }
                        }
                    }
                } else {
                    if (fs[0] <= 5.5) {
                        if (fs[47] <= 0.5) {
                            if (fs[87] <= 0.5) {
                                if (fs[72] <= 9898.5) {
                                    if (fs[4] <= 11.5) {
                                        return 0.00168351535867;
                                    } else {
                                        return -0.00433333848964;
                                    }
                                } else {
                                    if (fs[4] <= 19.5) {
                                        return 0.0172674398104;
                                    } else {
                                        return -0.01301391462;
                                    }
                                }
                            } else {
                                if (fs[12] <= 0.5) {
                                    if (fs[42] <= 0.5) {
                                        return 0.00561656954867;
                                    } else {
                                        return 0.0881790667048;
                                    }
                                } else {
                                    if (fs[82] <= 0.5) {
                                        return 0.0204836374578;
                                    } else {
                                        return 0.149228902071;
                                    }
                                }
                            }
                        } else {
                            if (fs[11] <= 0.5) {
                                if (fs[53] <= -1077.0) {
                                    return 0.118069357875;
                                } else {
                                    if (fs[4] <= 38.5) {
                                        return -0.0166598051811;
                                    } else {
                                        return 0.0281548442113;
                                    }
                                }
                            } else {
                                if (fs[91] <= 0.5) {
                                    if (fs[4] <= 9.5) {
                                        return -0.0104532903118;
                                    } else {
                                        return -0.00781412935028;
                                    }
                                } else {
                                    if (fs[2] <= 4.5) {
                                        return -0.0180694473198;
                                    } else {
                                        return 0.119984301521;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[4] <= 3.5) {
                            if (fs[85] <= 6.5) {
                                if (fs[100] <= 0.5) {
                                    if (fs[72] <= 9998.5) {
                                        return -0.00259949230633;
                                    } else {
                                        return 0.323376074907;
                                    }
                                } else {
                                    if (fs[53] <= -1138.5) {
                                        return 0.175067415456;
                                    } else {
                                        return -0.00266869223825;
                                    }
                                }
                            } else {
                                if (fs[52] <= 0.5) {
                                    if (fs[0] <= 10.5) {
                                        return 0.0566512485476;
                                    } else {
                                        return -0.00344346233887;
                                    }
                                } else {
                                    if (fs[0] <= 28.5) {
                                        return 0.039170380513;
                                    } else {
                                        return 0.147539926129;
                                    }
                                }
                            }
                        } else {
                            if (fs[0] <= 16.5) {
                                if (fs[57] <= 0.5) {
                                    if (fs[2] <= 2.5) {
                                        return -0.0041505122691;
                                    } else {
                                        return -0.0020043042299;
                                    }
                                } else {
                                    if (fs[53] <= -1062.0) {
                                        return 0.33999956606;
                                    } else {
                                        return 0.0278392826452;
                                    }
                                }
                            } else {
                                if (fs[76] <= 350.0) {
                                    if (fs[62] <= -0.5) {
                                        return 0.00544211212805;
                                    } else {
                                        return -0.00454756120762;
                                    }
                                } else {
                                    if (fs[0] <= 17.5) {
                                        return 0.00551474416695;
                                    } else {
                                        return -0.0113688651683;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                return 0.457456116671;
            }
        }
    }
}
