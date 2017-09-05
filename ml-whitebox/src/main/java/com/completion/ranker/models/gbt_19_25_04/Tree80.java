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

public class Tree80 {
    public double calcTree(double... fs) {
        if (fs[4] <= 7.5) {
            if (fs[0] <= 0.5) {
                if (fs[40] <= 0.5) {
                    if (fs[91] <= 0.5) {
                        if (fs[70] <= -3.5) {
                            if (fs[4] <= 3.5) {
                                if (fs[76] <= 250.0) {
                                    if (fs[18] <= 0.5) {
                                        return -0.0226043248125;
                                    } else {
                                        return 0.149283683101;
                                    }
                                } else {
                                    return -0.0490453217487;
                                }
                            } else {
                                if (fs[18] <= 0.5) {
                                    if (fs[4] <= 4.5) {
                                        return -0.250441759557;
                                    } else {
                                        return -0.0208298514985;
                                    }
                                } else {
                                    if (fs[100] <= 0.5) {
                                        return 0.0513337994651;
                                    } else {
                                        return -0.199637988811;
                                    }
                                }
                            }
                        } else {
                            if (fs[74] <= 0.5) {
                                if (fs[97] <= 0.5) {
                                    if (fs[57] <= 0.5) {
                                        return 0.0157971473109;
                                    } else {
                                        return 0.175849420292;
                                    }
                                } else {
                                    if (fs[15] <= 0.5) {
                                        return 0.0699896630045;
                                    } else {
                                        return -0.192797255429;
                                    }
                                }
                            } else {
                                if (fs[2] <= 3.5) {
                                    if (fs[53] <= -1118.5) {
                                        return -0.0293512868013;
                                    } else {
                                        return 0.18768800804;
                                    }
                                } else {
                                    if (fs[2] <= 4.5) {
                                        return 0.059684791201;
                                    } else {
                                        return 0.123590979312;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[76] <= 75.0) {
                            if (fs[72] <= 8930.0) {
                                if (fs[85] <= 0.5) {
                                    if (fs[4] <= 3.5) {
                                        return -0.148140510807;
                                    } else {
                                        return -0.187242763195;
                                    }
                                } else {
                                    return -0.394483221556;
                                }
                            } else {
                                return 0.136367785183;
                            }
                        } else {
                            return -0.254211193163;
                        }
                    }
                } else {
                    if (fs[70] <= -3.5) {
                        if (fs[4] <= 3.5) {
                            return -0.32862206588;
                        } else {
                            return -0.101899300947;
                        }
                    } else {
                        if (fs[4] <= 2.5) {
                            if (fs[52] <= 0.5) {
                                if (fs[33] <= 0.5) {
                                    return -0.0198541744507;
                                } else {
                                    return 0.217153108578;
                                }
                            } else {
                                if (fs[81] <= 0.5) {
                                    return -0.0615140404692;
                                } else {
                                    if (fs[53] <= -571.5) {
                                        return -0.309740643809;
                                    } else {
                                        return -0.297117190149;
                                    }
                                }
                            }
                        } else {
                            if (fs[4] <= 6.5) {
                                if (fs[68] <= 1.5) {
                                    if (fs[52] <= 0.5) {
                                        return -0.0376050862905;
                                    } else {
                                        return 0.0042781201406;
                                    }
                                } else {
                                    if (fs[102] <= 0.5) {
                                        return -0.31983579695;
                                    } else {
                                        return 0.0839397000502;
                                    }
                                }
                            } else {
                                if (fs[53] <= -1488.0) {
                                    if (fs[98] <= 1.5) {
                                        return -0.161718651134;
                                    } else {
                                        return -0.0838730044642;
                                    }
                                } else {
                                    if (fs[18] <= 0.5) {
                                        return 0.0322594792354;
                                    } else {
                                        return -0.187195025465;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (fs[76] <= 75.0) {
                    if (fs[78] <= 0.5) {
                        if (fs[59] <= 0.5) {
                            if (fs[52] <= 0.5) {
                                if (fs[4] <= 3.5) {
                                    if (fs[0] <= 2.5) {
                                        return -0.0737918962573;
                                    } else {
                                        return -0.0170274751735;
                                    }
                                } else {
                                    if (fs[11] <= 0.5) {
                                        return 0.0324783346296;
                                    } else {
                                        return -0.0130574808382;
                                    }
                                }
                            } else {
                                if (fs[71] <= 0.5) {
                                    if (fs[2] <= 1.5) {
                                        return -0.0314451217907;
                                    } else {
                                        return -0.0643326430979;
                                    }
                                } else {
                                    if (fs[0] <= 1.5) {
                                        return 0.0408432188047;
                                    } else {
                                        return 0.00316652033566;
                                    }
                                }
                            }
                        } else {
                            if (fs[2] <= 2.5) {
                                if (fs[0] <= 37.5) {
                                    if (fs[40] <= 0.5) {
                                        return 0.0469425576212;
                                    } else {
                                        return 0.31553058231;
                                    }
                                } else {
                                    return 0.378998547828;
                                }
                            } else {
                                if (fs[4] <= 5.5) {
                                    return 0.32897890422;
                                } else {
                                    return 0.387857519754;
                                }
                            }
                        }
                    } else {
                        if (fs[55] <= -1.5) {
                            if (fs[0] <= 1.5) {
                                if (fs[4] <= 4.5) {
                                    return 0.447148328738;
                                } else {
                                    if (fs[55] <= -2.5) {
                                        return 0.0556394984923;
                                    } else {
                                        return 0.334406994237;
                                    }
                                }
                            } else {
                                if (fs[47] <= 0.5) {
                                    if (fs[4] <= 4.5) {
                                        return 0.0242767924767;
                                    } else {
                                        return 0.14588942348;
                                    }
                                } else {
                                    if (fs[4] <= 5.5) {
                                        return -0.0189314438094;
                                    } else {
                                        return -0.0291369497174;
                                    }
                                }
                            }
                        } else {
                            if (fs[2] <= 6.5) {
                                if (fs[98] <= 1.5) {
                                    if (fs[85] <= 1.5) {
                                        return -0.00247059164895;
                                    } else {
                                        return 0.00420675858369;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return 0.0030163752308;
                                    } else {
                                        return 0.0172773601367;
                                    }
                                }
                            } else {
                                if (fs[87] <= 0.5) {
                                    if (fs[0] <= 1.5) {
                                        return -0.0543699763044;
                                    } else {
                                        return -0.0206825082777;
                                    }
                                } else {
                                    return -0.112125667163;
                                }
                            }
                        }
                    }
                } else {
                    if (fs[2] <= 1.5) {
                        if (fs[52] <= 0.5) {
                            if (fs[85] <= 4.5) {
                                if (fs[53] <= -1448.5) {
                                    if (fs[11] <= 0.5) {
                                        return 0.0743049007753;
                                    } else {
                                        return 0.0179989163493;
                                    }
                                } else {
                                    if (fs[53] <= -1143.5) {
                                        return -0.0429197971816;
                                    } else {
                                        return -0.00164068749947;
                                    }
                                }
                            } else {
                                if (fs[47] <= 0.5) {
                                    if (fs[53] <= -1488.0) {
                                        return -0.0343850964334;
                                    } else {
                                        return -0.00998030485272;
                                    }
                                } else {
                                    if (fs[39] <= 0.5) {
                                        return -0.00424672758535;
                                    } else {
                                        return -0.0240557299803;
                                    }
                                }
                            }
                        } else {
                            if (fs[85] <= 6.5) {
                                if (fs[53] <= -1493.5) {
                                    return 0.301674284255;
                                } else {
                                    if (fs[85] <= 5.5) {
                                        return 0.0150309547944;
                                    } else {
                                        return -0.00789562379774;
                                    }
                                }
                            } else {
                                if (fs[40] <= 0.5) {
                                    if (fs[43] <= 0.5) {
                                        return 0.0287708036391;
                                    } else {
                                        return -0.0858652721062;
                                    }
                                } else {
                                    if (fs[53] <= -1218.0) {
                                        return 0.207833994135;
                                    } else {
                                        return -0.0197428813257;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[52] <= 0.5) {
                            if (fs[43] <= 0.5) {
                                if (fs[2] <= 6.5) {
                                    if (fs[58] <= 0.5) {
                                        return -0.00477164735009;
                                    } else {
                                        return -0.0654638239871;
                                    }
                                } else {
                                    return -0.210934532933;
                                }
                            } else {
                                if (fs[87] <= 0.5) {
                                    if (fs[0] <= 1.5) {
                                        return -0.0959539405466;
                                    } else {
                                        return -0.0144457954594;
                                    }
                                } else {
                                    if (fs[53] <= -1262.5) {
                                        return -0.133048569592;
                                    } else {
                                        return -0.0446893444876;
                                    }
                                }
                            }
                        } else {
                            if (fs[47] <= 0.5) {
                                if (fs[53] <= -1468.5) {
                                    if (fs[4] <= 6.5) {
                                        return 0.122588274774;
                                    } else {
                                        return 0.0344644499375;
                                    }
                                } else {
                                    if (fs[25] <= 0.5) {
                                        return 0.0511090749615;
                                    } else {
                                        return -0.0635044905938;
                                    }
                                }
                            } else {
                                if (fs[25] <= 0.5) {
                                    if (fs[2] <= 2.5) {
                                        return -0.0169242287046;
                                    } else {
                                        return -0.0330296431466;
                                    }
                                } else {
                                    if (fs[72] <= 9051.5) {
                                        return -0.0112817175042;
                                    } else {
                                        return -0.0177884954525;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (fs[27] <= 0.5) {
                if (fs[49] <= -1.5) {
                    if (fs[60] <= 0.5) {
                        if (fs[76] <= 250.0) {
                            if (fs[2] <= 1.5) {
                                return 0.0158108652691;
                            } else {
                                if (fs[64] <= -996.5) {
                                    return 0.10800514039;
                                } else {
                                    return 0.119467045083;
                                }
                            }
                        } else {
                            if (fs[0] <= 0.5) {
                                if (fs[4] <= 16.5) {
                                    if (fs[4] <= 13.5) {
                                        return -0.153560441197;
                                    } else {
                                        return -0.385329054575;
                                    }
                                } else {
                                    return 0.107425218432;
                                }
                            } else {
                                if (fs[100] <= 1.5) {
                                    if (fs[64] <= -996.5) {
                                        return 0.0191315043668;
                                    } else {
                                        return -0.0162329673432;
                                    }
                                } else {
                                    if (fs[64] <= -997.5) {
                                        return 0.0704072312467;
                                    } else {
                                        return -0.043396140206;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[12] <= 0.5) {
                            if (fs[4] <= 9.5) {
                                return 0.118696736916;
                            } else {
                                if (fs[31] <= 0.5) {
                                    if (fs[4] <= 17.5) {
                                        return 0.0339359933378;
                                    } else {
                                        return -0.0190924534752;
                                    }
                                } else {
                                    return -0.0787250053126;
                                }
                            }
                        } else {
                            if (fs[53] <= -1283.0) {
                                if (fs[4] <= 17.5) {
                                    return 0.0977658675985;
                                } else {
                                    if (fs[94] <= 0.5) {
                                        return 0.00333453051789;
                                    } else {
                                        return -0.242330335682;
                                    }
                                }
                            } else {
                                if (fs[47] <= 0.5) {
                                    if (fs[0] <= 5.5) {
                                        return 0.0986923533536;
                                    } else {
                                        return 0.318402352418;
                                    }
                                } else {
                                    if (fs[31] <= 0.5) {
                                        return -0.0259619218977;
                                    } else {
                                        return 0.0418129307387;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[2] <= 4.5) {
                        if (fs[55] <= 0.5) {
                            if (fs[80] <= 0.5) {
                                if (fs[64] <= -998.5) {
                                    if (fs[98] <= 1.0) {
                                        return -0.0831984999901;
                                    } else {
                                        return 0.072048159743;
                                    }
                                } else {
                                    if (fs[23] <= 0.5) {
                                        return -0.00128839396407;
                                    } else {
                                        return 0.0436650998547;
                                    }
                                }
                            } else {
                                if (fs[12] <= 0.5) {
                                    if (fs[72] <= 9999.5) {
                                        return -0.0189018622777;
                                    } else {
                                        return 0.0400779438998;
                                    }
                                } else {
                                    if (fs[40] <= 0.5) {
                                        return 0.0214642929085;
                                    } else {
                                        return 0.22453143719;
                                    }
                                }
                            }
                        } else {
                            if (fs[0] <= 1.5) {
                                if (fs[4] <= 11.5) {
                                    if (fs[85] <= 2.0) {
                                        return -0.0715639755581;
                                    } else {
                                        return 0.153364732907;
                                    }
                                } else {
                                    if (fs[72] <= 4984.0) {
                                        return 0.13719827904;
                                    } else {
                                        return 0.278072515613;
                                    }
                                }
                            } else {
                                if (fs[33] <= 0.5) {
                                    return 0.0286843055664;
                                } else {
                                    return -0.0183890678213;
                                }
                            }
                        }
                    } else {
                        if (fs[4] <= 11.5) {
                            if (fs[72] <= 7904.5) {
                                if (fs[0] <= 3.5) {
                                    if (fs[53] <= -1478.0) {
                                        return 0.0450291266221;
                                    } else {
                                        return 0.0182175000957;
                                    }
                                } else {
                                    if (fs[53] <= -1488.0) {
                                        return 0.0189695571543;
                                    } else {
                                        return -0.00063194511073;
                                    }
                                }
                            } else {
                                if (fs[42] <= 0.5) {
                                    if (fs[4] <= 8.5) {
                                        return 0.117145815099;
                                    } else {
                                        return 0.0546871233206;
                                    }
                                } else {
                                    if (fs[2] <= 6.5) {
                                        return -0.10431813306;
                                    } else {
                                        return -0.162835709426;
                                    }
                                }
                            }
                        } else {
                            if (fs[12] <= 0.5) {
                                if (fs[102] <= 0.5) {
                                    if (fs[2] <= 11.5) {
                                        return 0.00160018957777;
                                    } else {
                                        return 0.0207252556791;
                                    }
                                } else {
                                    if (fs[0] <= 0.5) {
                                        return -0.0398632303374;
                                    } else {
                                        return -0.00216082759758;
                                    }
                                }
                            } else {
                                if (fs[28] <= 0.5) {
                                    if (fs[87] <= 0.5) {
                                        return 0.0111308209852;
                                    } else {
                                        return 0.0464343301485;
                                    }
                                } else {
                                    if (fs[53] <= -21.0) {
                                        return -0.0125818608274;
                                    } else {
                                        return 0.127728960389;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                return 0.437425452452;
            }
        }
    }
}
