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

public class Tree61 {
    public double calcTree(double... fs) {
        if (fs[78] <= 0.5) {
            if (fs[98] <= 0.5) {
                if (fs[30] <= 0.5) {
                    if (fs[0] <= 0.5) {
                        if (fs[4] <= 7.5) {
                            if (fs[11] <= 0.5) {
                                if (fs[4] <= 5.5) {
                                    if (fs[4] <= 4.5) {
                                        return 0.0469676492716;
                                    } else {
                                        return 0.0657373961175;
                                    }
                                } else {
                                    if (fs[4] <= 6.5) {
                                        return 0.157960725801;
                                    } else {
                                        return 0.0405443761727;
                                    }
                                }
                            } else {
                                if (fs[60] <= 0.5) {
                                    if (fs[4] <= 6.5) {
                                        return 0.0474034669001;
                                    } else {
                                        return 0.0286692443261;
                                    }
                                } else {
                                    if (fs[53] <= -1138.5) {
                                        return 0.0258796488615;
                                    } else {
                                        return 0.0453235660501;
                                    }
                                }
                            }
                        } else {
                            if (fs[72] <= 9998.5) {
                                if (fs[34] <= 0.5) {
                                    if (fs[2] <= 1.5) {
                                        return 0.155525322022;
                                    } else {
                                        return 0.0778490350149;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return -0.134603083264;
                                    } else {
                                        return 0.0785810292632;
                                    }
                                }
                            } else {
                                if (fs[2] <= 1.5) {
                                    return -0.177091689224;
                                } else {
                                    return -0.191519717028;
                                }
                            }
                        }
                    } else {
                        if (fs[4] <= 2.5) {
                            if (fs[0] <= 5.5) {
                                if (fs[40] <= 0.5) {
                                    return 0.138626360671;
                                } else {
                                    return -0.12040427492;
                                }
                            } else {
                                return 0.0287798392981;
                            }
                        } else {
                            if (fs[0] <= 3.5) {
                                if (fs[34] <= 0.5) {
                                    if (fs[53] <= -476.5) {
                                        return 0.0134453215046;
                                    } else {
                                        return -0.0100165964627;
                                    }
                                } else {
                                    if (fs[52] <= 0.5) {
                                        return 0.039109435793;
                                    } else {
                                        return 0.133351045303;
                                    }
                                }
                            } else {
                                if (fs[18] <= 0.5) {
                                    if (fs[40] <= 0.5) {
                                        return -0.00614867185023;
                                    } else {
                                        return -0.035195302591;
                                    }
                                } else {
                                    if (fs[2] <= 2.5) {
                                        return 0.0158975018645;
                                    } else {
                                        return 0.111684371353;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[71] <= 0.5) {
                        if (fs[0] <= 0.5) {
                            return -0.204876206478;
                        } else {
                            if (fs[4] <= 8.0) {
                                if (fs[2] <= 1.5) {
                                    if (fs[52] <= 0.5) {
                                        return -0.00242237935629;
                                    } else {
                                        return 0.0148100618204;
                                    }
                                } else {
                                    if (fs[0] <= 3.5) {
                                        return -0.0354123292857;
                                    } else {
                                        return -0.00469890517472;
                                    }
                                }
                            } else {
                                if (fs[0] <= 61.0) {
                                    return -0.00739379595131;
                                } else {
                                    return -0.0150451189704;
                                }
                            }
                        }
                    } else {
                        if (fs[52] <= 0.5) {
                            if (fs[0] <= 2.5) {
                                if (fs[40] <= 0.5) {
                                    if (fs[4] <= 6.5) {
                                        return -0.0572943868064;
                                    } else {
                                        return -0.0249500339864;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return -0.0757107449072;
                                    } else {
                                        return -0.131346070777;
                                    }
                                }
                            } else {
                                if (fs[4] <= 4.5) {
                                    return -0.0121885185875;
                                } else {
                                    if (fs[0] <= 8.5) {
                                        return -0.00871823880575;
                                    } else {
                                        return 0.000217120994354;
                                    }
                                }
                            }
                        } else {
                            if (fs[2] <= 1.5) {
                                if (fs[0] <= 2.5) {
                                    return -0.0938373823331;
                                } else {
                                    return -0.0435507327308;
                                }
                            } else {
                                return -0.0204734578729;
                            }
                        }
                    }
                }
            } else {
                if (fs[53] <= -1313.5) {
                    return 0.35846322604;
                } else {
                    if (fs[62] <= -0.5) {
                        if (fs[0] <= 0.5) {
                            if (fs[64] <= -997.5) {
                                return 0.0548232468459;
                            } else {
                                return 0.165849488946;
                            }
                        } else {
                            if (fs[0] <= 1.5) {
                                return -0.0989912481137;
                            } else {
                                if (fs[4] <= 9.5) {
                                    return -0.0124182000855;
                                } else {
                                    return 0.0857595968424;
                                }
                            }
                        }
                    } else {
                        if (fs[96] <= 0.5) {
                            if (fs[31] <= 0.5) {
                                if (fs[4] <= 7.5) {
                                    if (fs[4] <= 4.5) {
                                        return -0.0549105077417;
                                    } else {
                                        return -0.0892332000162;
                                    }
                                } else {
                                    if (fs[72] <= 9913.0) {
                                        return -0.00519587883622;
                                    } else {
                                        return 0.0397526749232;
                                    }
                                }
                            } else {
                                if (fs[2] <= 1.5) {
                                    if (fs[52] <= 0.5) {
                                        return 0.172511053617;
                                    } else {
                                        return 0.204249133119;
                                    }
                                } else {
                                    return 0.0679588613458;
                                }
                            }
                        } else {
                            if (fs[53] <= -1138.0) {
                                if (fs[4] <= 7.5) {
                                    if (fs[4] <= 6.5) {
                                        return -0.0131002679214;
                                    } else {
                                        return 0.0523309652826;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return -0.147138510977;
                                    } else {
                                        return -0.00827489399756;
                                    }
                                }
                            } else {
                                if (fs[0] <= 1.5) {
                                    if (fs[52] <= 0.5) {
                                        return 0.0923497668528;
                                    } else {
                                        return -0.0797585058198;
                                    }
                                } else {
                                    if (fs[4] <= 10.5) {
                                        return -0.00151944742804;
                                    } else {
                                        return -0.0212782301872;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (fs[64] <= -997.5) {
                if (fs[98] <= 0.5) {
                    if (fs[0] <= 0.5) {
                        if (fs[51] <= 0.5) {
                            if (fs[4] <= 9.5) {
                                if (fs[85] <= -0.5) {
                                    return -0.265507330117;
                                } else {
                                    if (fs[72] <= 5000.0) {
                                        return -0.0414941818639;
                                    } else {
                                        return 0.125548140475;
                                    }
                                }
                            } else {
                                if (fs[2] <= 1.5) {
                                    if (fs[102] <= 0.5) {
                                        return 0.0634902619155;
                                    } else {
                                        return 0.161708556334;
                                    }
                                } else {
                                    if (fs[33] <= 0.5) {
                                        return 0.0831952441609;
                                    } else {
                                        return 0.296700232292;
                                    }
                                }
                            }
                        } else {
                            return 0.226309262979;
                        }
                    } else {
                        if (fs[51] <= 0.5) {
                            if (fs[29] <= 0.5) {
                                if (fs[0] <= 2.5) {
                                    if (fs[2] <= 2.5) {
                                        return 0.0145726714942;
                                    } else {
                                        return 0.0836658110411;
                                    }
                                } else {
                                    if (fs[0] <= 3.5) {
                                        return -0.0418575262522;
                                    } else {
                                        return 0.00822007789495;
                                    }
                                }
                            } else {
                                if (fs[4] <= 13.5) {
                                    if (fs[47] <= 0.5) {
                                        return -0.0452169573072;
                                    } else {
                                        return -0.011082742147;
                                    }
                                } else {
                                    return 0.0501389974588;
                                }
                            }
                        } else {
                            if (fs[102] <= 0.5) {
                                if (fs[0] <= 1.5) {
                                    return -0.0831997711416;
                                } else {
                                    if (fs[0] <= 2.5) {
                                        return -0.0489566529848;
                                    } else {
                                        return -0.0266123133856;
                                    }
                                }
                            } else {
                                return 0.0133544811429;
                            }
                        }
                    }
                } else {
                    if (fs[11] <= 0.5) {
                        if (fs[47] <= 0.5) {
                            if (fs[100] <= 1.5) {
                                if (fs[33] <= 0.5) {
                                    if (fs[96] <= 0.5) {
                                        return 0.100871364095;
                                    } else {
                                        return -0.0338178898024;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return 0.131680317863;
                                    } else {
                                        return 0.159256793577;
                                    }
                                }
                            } else {
                                if (fs[33] <= 0.5) {
                                    if (fs[4] <= 10.5) {
                                        return 0.0486463529217;
                                    } else {
                                        return 0.109752987124;
                                    }
                                } else {
                                    return -0.129336124564;
                                }
                            }
                        } else {
                            if (fs[2] <= 1.5) {
                                if (fs[53] <= -986.5) {
                                    if (fs[4] <= 10.5) {
                                        return -0.0196100400204;
                                    } else {
                                        return 0.0622191268346;
                                    }
                                } else {
                                    if (fs[53] <= -976.5) {
                                        return -0.0310504752001;
                                    } else {
                                        return -0.0118558894361;
                                    }
                                }
                            } else {
                                if (fs[60] <= 0.5) {
                                    if (fs[100] <= 1.5) {
                                        return -0.0182398785342;
                                    } else {
                                        return -0.00948299071851;
                                    }
                                } else {
                                    if (fs[0] <= 1.5) {
                                        return -0.0368159332918;
                                    } else {
                                        return -0.0291267579315;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[72] <= 5000.0) {
                            if (fs[53] <= -1478.0) {
                                if (fs[2] <= 3.5) {
                                    return -0.0928967431248;
                                } else {
                                    return -0.273966262073;
                                }
                            } else {
                                if (fs[0] <= 0.5) {
                                    if (fs[4] <= 7.5) {
                                        return 0.020040014861;
                                    } else {
                                        return 0.097713268622;
                                    }
                                } else {
                                    if (fs[4] <= 10.5) {
                                        return 0.00640287899769;
                                    } else {
                                        return -0.0342392392815;
                                    }
                                }
                            }
                        } else {
                            return 0.249302508172;
                        }
                    }
                }
            } else {
                if (fs[76] <= 25.0) {
                    if (fs[0] <= 0.5) {
                        if (fs[85] <= 5.5) {
                            if (fs[103] <= 0.5) {
                                if (fs[102] <= 0.5) {
                                    if (fs[70] <= -1.5) {
                                        return 0.00997946818975;
                                    } else {
                                        return 0.305406138533;
                                    }
                                } else {
                                    if (fs[85] <= 0.5) {
                                        return -0.0942011158432;
                                    } else {
                                        return -0.0147153682689;
                                    }
                                }
                            } else {
                                if (fs[74] <= 0.5) {
                                    if (fs[53] <= -2493.0) {
                                        return -0.0577204023602;
                                    } else {
                                        return 0.079898350608;
                                    }
                                } else {
                                    if (fs[2] <= 3.5) {
                                        return -0.0252934504064;
                                    } else {
                                        return 0.0810356232915;
                                    }
                                }
                            }
                        } else {
                            if (fs[53] <= -1093.5) {
                                if (fs[28] <= 0.5) {
                                    if (fs[4] <= 21.5) {
                                        return 0.136598055014;
                                    } else {
                                        return -0.126441671198;
                                    }
                                } else {
                                    return -0.363288913078;
                                }
                            } else {
                                if (fs[11] <= 0.5) {
                                    if (fs[2] <= 1.5) {
                                        return -0.0251686805023;
                                    } else {
                                        return 0.125729405163;
                                    }
                                } else {
                                    if (fs[2] <= 2.5) {
                                        return 0.00221377471247;
                                    } else {
                                        return 0.0827405939728;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[57] <= 0.5) {
                            if (fs[85] <= -0.5) {
                                if (fs[0] <= 2.5) {
                                    if (fs[12] <= 0.5) {
                                        return -0.0169877170094;
                                    } else {
                                        return -0.0351150241448;
                                    }
                                } else {
                                    if (fs[4] <= 9.5) {
                                        return -0.00444972769676;
                                    } else {
                                        return -0.00287473319857;
                                    }
                                }
                            } else {
                                if (fs[33] <= 0.5) {
                                    if (fs[72] <= 9997.5) {
                                        return -0.00296764258069;
                                    } else {
                                        return 0.0196721302381;
                                    }
                                } else {
                                    if (fs[2] <= 3.5) {
                                        return -0.0011649610987;
                                    } else {
                                        return 0.0060172546539;
                                    }
                                }
                            }
                        } else {
                            if (fs[4] <= 8.5) {
                                if (fs[72] <= 9574.0) {
                                    if (fs[53] <= -561.5) {
                                        return -0.0847734099596;
                                    } else {
                                        return 0.00719686282951;
                                    }
                                } else {
                                    return 0.262306402418;
                                }
                            } else {
                                if (fs[98] <= 0.5) {
                                    if (fs[53] <= -1067.0) {
                                        return 0.492588150092;
                                    } else {
                                        return 0.00281874491859;
                                    }
                                } else {
                                    if (fs[11] <= 0.5) {
                                        return 0.548639755713;
                                    } else {
                                        return 0.192572205275;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[0] <= 0.5) {
                        if (fs[2] <= 1.5) {
                            if (fs[53] <= -1493.5) {
                                if (fs[2] <= 0.5) {
                                    return -0.178995557028;
                                } else {
                                    if (fs[52] <= 0.5) {
                                        return 0.0799286376369;
                                    } else {
                                        return 0.126176282465;
                                    }
                                }
                            } else {
                                if (fs[53] <= -1483.5) {
                                    if (fs[37] <= 0.5) {
                                        return -0.0446062815734;
                                    } else {
                                        return 0.154477728685;
                                    }
                                } else {
                                    if (fs[96] <= 0.5) {
                                        return 0.0698674509807;
                                    } else {
                                        return 0.0196924456683;
                                    }
                                }
                            }
                        } else {
                            if (fs[37] <= 0.5) {
                                if (fs[53] <= -1493.5) {
                                    if (fs[98] <= 0.5) {
                                        return 0.204685759004;
                                    } else {
                                        return 0.115235150241;
                                    }
                                } else {
                                    if (fs[4] <= 17.5) {
                                        return 0.0436565373411;
                                    } else {
                                        return -0.00118692534341;
                                    }
                                }
                            } else {
                                if (fs[98] <= 0.5) {
                                    if (fs[72] <= 9999.5) {
                                        return 0.119670483913;
                                    } else {
                                        return 0.000864868417071;
                                    }
                                } else {
                                    if (fs[2] <= 2.5) {
                                        return 0.156952378281;
                                    } else {
                                        return 0.264646708223;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[2] <= 3.5) {
                            if (fs[4] <= 6.5) {
                                if (fs[2] <= 1.5) {
                                    if (fs[47] <= 0.5) {
                                        return 0.00601150840574;
                                    } else {
                                        return -0.00672061452548;
                                    }
                                } else {
                                    if (fs[53] <= -1488.0) {
                                        return 0.0920670124607;
                                    } else {
                                        return 0.00753871893341;
                                    }
                                }
                            } else {
                                if (fs[72] <= 9999.5) {
                                    if (fs[85] <= 7.5) {
                                        return -0.00269401099525;
                                    } else {
                                        return -0.0191900101285;
                                    }
                                } else {
                                    if (fs[53] <= -1108.0) {
                                        return 0.0643967865013;
                                    } else {
                                        return -0.00426062810414;
                                    }
                                }
                            }
                        } else {
                            if (fs[40] <= 0.5) {
                                if (fs[4] <= 8.5) {
                                    if (fs[4] <= 4.5) {
                                        return -0.0861665005677;
                                    } else {
                                        return 0.0811839186899;
                                    }
                                } else {
                                    if (fs[4] <= 11.5) {
                                        return 0.0198622099289;
                                    } else {
                                        return 0.00203718454388;
                                    }
                                }
                            } else {
                                if (fs[0] <= 1.5) {
                                    if (fs[53] <= -1488.0) {
                                        return 0.161225096321;
                                    } else {
                                        return 0.0576079581546;
                                    }
                                } else {
                                    if (fs[4] <= 18.5) {
                                        return 0.0356201466495;
                                    } else {
                                        return -0.0186216061059;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
