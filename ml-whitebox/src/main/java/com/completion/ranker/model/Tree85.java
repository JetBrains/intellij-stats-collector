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

public class Tree85 {
    public double calcTree(double... fs) {
        if (fs[0] <= 0.5) {
            if (fs[85] <= 6.5) {
                if (fs[57] <= 0.5) {
                    if (fs[53] <= -1498.5) {
                        if (fs[53] <= -1953.5) {
                            if (fs[18] <= 0.5) {
                                if (fs[72] <= 8584.0) {
                                    if (fs[52] <= 0.5) {
                                        return -0.0797097885438;
                                    } else {
                                        return -0.00340348419641;
                                    }
                                } else {
                                    if (fs[72] <= 9992.5) {
                                        return 0.165182653376;
                                    } else {
                                        return 0.0229838532502;
                                    }
                                }
                            } else {
                                if (fs[70] <= -3.5) {
                                    if (fs[72] <= 9997.0) {
                                        return 0.0927006296733;
                                    } else {
                                        return -0.216810123587;
                                    }
                                } else {
                                    if (fs[87] <= 0.5) {
                                        return 0.256974765477;
                                    } else {
                                        return 0.0768111971723;
                                    }
                                }
                            }
                        } else {
                            if (fs[87] <= 0.5) {
                                if (fs[85] <= 3.5) {
                                    if (fs[4] <= 14.5) {
                                        return 0.106932033111;
                                    } else {
                                        return 0.0281539897584;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return 0.1193094974;
                                    } else {
                                        return 0.241138026067;
                                    }
                                }
                            } else {
                                if (fs[52] <= 0.5) {
                                    if (fs[53] <= -1568.0) {
                                        return -0.00913443408982;
                                    } else {
                                        return 0.0974133913962;
                                    }
                                } else {
                                    if (fs[43] <= 0.5) {
                                        return 0.0836928792442;
                                    } else {
                                        return -0.0209836693134;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[2] <= 5.5) {
                            if (fs[102] <= 0.5) {
                                if (fs[4] <= 6.5) {
                                    if (fs[25] <= 0.5) {
                                        return 0.017763875663;
                                    } else {
                                        return -0.0164984205143;
                                    }
                                } else {
                                    if (fs[43] <= 0.5) {
                                        return -0.00452689285215;
                                    } else {
                                        return 0.0349990932313;
                                    }
                                }
                            } else {
                                if (fs[72] <= 9966.5) {
                                    if (fs[71] <= 0.5) {
                                        return -0.012318680062;
                                    } else {
                                        return -0.0791616289573;
                                    }
                                } else {
                                    if (fs[12] <= 0.5) {
                                        return 0.00212120843548;
                                    } else {
                                        return 0.0673395897979;
                                    }
                                }
                            }
                        } else {
                            if (fs[28] <= 0.5) {
                                if (fs[4] <= 6.5) {
                                    if (fs[85] <= 0.5) {
                                        return 0.0140012996778;
                                    } else {
                                        return -0.15051610462;
                                    }
                                } else {
                                    if (fs[42] <= 0.5) {
                                        return 0.0435880002908;
                                    } else {
                                        return -0.0333627740247;
                                    }
                                }
                            } else {
                                if (fs[72] <= 5000.0) {
                                    if (fs[4] <= 17.0) {
                                        return -0.279773327512;
                                    } else {
                                        return -0.142568722869;
                                    }
                                } else {
                                    return -0.335302543347;
                                }
                            }
                        }
                    }
                } else {
                    if (fs[100] <= 1.5) {
                        if (fs[12] <= 0.5) {
                            if (fs[96] <= 0.5) {
                                if (fs[4] <= 8.5) {
                                    if (fs[98] <= 0.5) {
                                        return 0.109623242865;
                                    } else {
                                        return 0.290698190982;
                                    }
                                } else {
                                    if (fs[98] <= 0.5) {
                                        return 0.170825755178;
                                    } else {
                                        return 0.400881400628;
                                    }
                                }
                            } else {
                                if (fs[2] <= 1.5) {
                                    return 0.0609959444127;
                                } else {
                                    if (fs[2] <= 2.5) {
                                        return 0.176068365708;
                                    } else {
                                        return 0.140849186844;
                                    }
                                }
                            }
                        } else {
                            if (fs[2] <= 4.5) {
                                if (fs[96] <= 0.5) {
                                    if (fs[18] <= 0.5) {
                                        return -0.0225759348058;
                                    } else {
                                        return 0.108240997993;
                                    }
                                } else {
                                    if (fs[100] <= 0.5) {
                                        return 0.130594144573;
                                    } else {
                                        return 0.0726013406297;
                                    }
                                }
                            } else {
                                return 0.0351492421911;
                            }
                        }
                    } else {
                        if (fs[70] <= -4.0) {
                            return -0.346918836237;
                        } else {
                            return 0.163447323251;
                        }
                    }
                }
            } else {
                if (fs[60] <= 0.5) {
                    if (fs[82] <= 0.5) {
                        return -0.0798732652624;
                    } else {
                        if (fs[4] <= 4.5) {
                            if (fs[43] <= 0.5) {
                                if (fs[2] <= 2.5) {
                                    if (fs[72] <= 9869.5) {
                                        return 0.245943837098;
                                    } else {
                                        return 0.101876066287;
                                    }
                                } else {
                                    if (fs[2] <= 3.5) {
                                        return 0.10780359158;
                                    } else {
                                        return 0.0907878698929;
                                    }
                                }
                            } else {
                                return -0.21701853392;
                            }
                        } else {
                            if (fs[53] <= -1488.0) {
                                if (fs[25] <= 0.5) {
                                    if (fs[2] <= 2.5) {
                                        return 0.169271253533;
                                    } else {
                                        return -0.000186647608311;
                                    }
                                } else {
                                    if (fs[4] <= 5.5) {
                                        return -0.390639099279;
                                    } else {
                                        return 0.0328931484743;
                                    }
                                }
                            } else {
                                if (fs[72] <= 9993.5) {
                                    if (fs[53] <= -1448.5) {
                                        return 0.0946556402049;
                                    } else {
                                        return 0.197988550396;
                                    }
                                } else {
                                    if (fs[53] <= -1453.5) {
                                        return 0.0143743411304;
                                    } else {
                                        return 0.095292896351;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[2] <= 1.5) {
                        if (fs[53] <= -963.5) {
                            if (fs[71] <= 0.5) {
                                if (fs[52] <= 0.5) {
                                    if (fs[72] <= 9999.5) {
                                        return 0.20944780773;
                                    } else {
                                        return 0.0991056983003;
                                    }
                                } else {
                                    if (fs[18] <= 0.5) {
                                        return -0.0507168125477;
                                    } else {
                                        return 0.104488265756;
                                    }
                                }
                            } else {
                                if (fs[72] <= 9983.5) {
                                    if (fs[85] <= 7.5) {
                                        return 0.0167692470925;
                                    } else {
                                        return -0.107732053203;
                                    }
                                } else {
                                    if (fs[85] <= 7.5) {
                                        return 0.134101156744;
                                    } else {
                                        return -0.0282844076731;
                                    }
                                }
                            }
                        } else {
                            if (fs[85] <= 7.5) {
                                if (fs[40] <= 0.5) {
                                    if (fs[11] <= 0.5) {
                                        return 0.022161964512;
                                    } else {
                                        return -0.0732712039669;
                                    }
                                } else {
                                    return -0.186976161423;
                                }
                            } else {
                                if (fs[64] <= -498.0) {
                                    return 0.164570982247;
                                } else {
                                    if (fs[72] <= 4943.5) {
                                        return -0.163600108626;
                                    } else {
                                        return -0.0387856234791;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[33] <= 0.5) {
                            if (fs[4] <= 8.5) {
                                if (fs[71] <= 0.5) {
                                    if (fs[53] <= -1488.0) {
                                        return 0.10357623064;
                                    } else {
                                        return 0.0499536853161;
                                    }
                                } else {
                                    if (fs[4] <= 5.5) {
                                        return -0.0479532487911;
                                    } else {
                                        return 0.0412876276872;
                                    }
                                }
                            } else {
                                if (fs[72] <= 9978.5) {
                                    if (fs[4] <= 10.5) {
                                        return -0.182515355016;
                                    } else {
                                        return 0.0304659999299;
                                    }
                                } else {
                                    if (fs[4] <= 10.5) {
                                        return -0.054517008303;
                                    } else {
                                        return 0.134992279454;
                                    }
                                }
                            }
                        } else {
                            if (fs[18] <= 0.5) {
                                if (fs[11] <= 0.5) {
                                    return 0.0944534525005;
                                } else {
                                    if (fs[4] <= 11.5) {
                                        return 0.094106136623;
                                    } else {
                                        return 0.46094012806;
                                    }
                                }
                            } else {
                                if (fs[4] <= 19.5) {
                                    if (fs[11] <= 0.5) {
                                        return 0.0768552560475;
                                    } else {
                                        return 0.00608986938926;
                                    }
                                } else {
                                    if (fs[52] <= 0.5) {
                                        return -0.24339578282;
                                    } else {
                                        return 0.0122494978918;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (fs[55] <= 998.5) {
                if (fs[58] <= 0.5) {
                    if (fs[12] <= 0.5) {
                        if (fs[51] <= 0.5) {
                            if (fs[52] <= 0.5) {
                                if (fs[80] <= 0.5) {
                                    if (fs[85] <= 7.5) {
                                        return -0.00182932422637;
                                    } else {
                                        return -0.00996395188108;
                                    }
                                } else {
                                    if (fs[47] <= 0.5) {
                                        return -0.0349654444718;
                                    } else {
                                        return 0.00320542621195;
                                    }
                                }
                            } else {
                                if (fs[87] <= 0.5) {
                                    if (fs[85] <= 7.5) {
                                        return -0.000473485495815;
                                    } else {
                                        return 0.00806750276172;
                                    }
                                } else {
                                    if (fs[53] <= -1428.0) {
                                        return 0.0250447649592;
                                    } else {
                                        return 0.00160299050002;
                                    }
                                }
                            }
                        } else {
                            if (fs[0] <= 2.5) {
                                if (fs[2] <= 3.5) {
                                    if (fs[4] <= 7.5) {
                                        return 0.130828585476;
                                    } else {
                                        return 0.0147682623788;
                                    }
                                } else {
                                    if (fs[53] <= -1128.0) {
                                        return -0.0918047327262;
                                    } else {
                                        return 0.0104476306826;
                                    }
                                }
                            } else {
                                if (fs[87] <= 0.5) {
                                    if (fs[0] <= 123.0) {
                                        return 0.000473238405613;
                                    } else {
                                        return 0.122208695308;
                                    }
                                } else {
                                    if (fs[0] <= 4.5) {
                                        return 0.156302941741;
                                    } else {
                                        return 0.00758805668826;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[29] <= 0.5) {
                            if (fs[57] <= 0.5) {
                                if (fs[70] <= -1.5) {
                                    if (fs[53] <= -1488.0) {
                                        return 0.00879218383668;
                                    } else {
                                        return 0.000842936830972;
                                    }
                                } else {
                                    return 0.387686798493;
                                }
                            } else {
                                if (fs[47] <= 0.5) {
                                    if (fs[96] <= 0.5) {
                                        return 0.0469730621897;
                                    } else {
                                        return 0.240680765455;
                                    }
                                } else {
                                    if (fs[53] <= -986.0) {
                                        return -0.00976121066978;
                                    } else {
                                        return -0.0409803166437;
                                    }
                                }
                            }
                        } else {
                            if (fs[94] <= 0.5) {
                                if (fs[72] <= 9788.5) {
                                    if (fs[98] <= 1.5) {
                                        return -0.00492304921688;
                                    } else {
                                        return -0.032150240679;
                                    }
                                } else {
                                    if (fs[64] <= -498.5) {
                                        return 0.0230535031735;
                                    } else {
                                        return -0.078517519911;
                                    }
                                }
                            } else {
                                if (fs[47] <= 0.5) {
                                    if (fs[0] <= 4.5) {
                                        return -0.0993339340935;
                                    } else {
                                        return -0.0673244208143;
                                    }
                                } else {
                                    return -0.006935563058;
                                }
                            }
                        }
                    }
                } else {
                    if (fs[47] <= 0.5) {
                        if (fs[62] <= -0.5) {
                            if (fs[4] <= 6.5) {
                                return -0.171100778645;
                            } else {
                                if (fs[4] <= 11.5) {
                                    if (fs[4] <= 7.5) {
                                        return -0.0866413877476;
                                    } else {
                                        return 0.0234665031871;
                                    }
                                } else {
                                    if (fs[0] <= 4.5) {
                                        return -0.10578806166;
                                    } else {
                                        return -0.0615211177692;
                                    }
                                }
                            }
                        } else {
                            if (fs[72] <= 9998.5) {
                                if (fs[87] <= 0.5) {
                                    return 0.0792662362907;
                                } else {
                                    if (fs[40] <= 0.5) {
                                        return -0.0439008830158;
                                    } else {
                                        return -0.0205567494504;
                                    }
                                }
                            } else {
                                if (fs[53] <= -1123.0) {
                                    if (fs[0] <= 1.5) {
                                        return -0.169372833988;
                                    } else {
                                        return -0.118820321466;
                                    }
                                } else {
                                    return -0.0728389860701;
                                }
                            }
                        }
                    } else {
                        if (fs[4] <= 8.5) {
                            if (fs[100] <= 1.5) {
                                return -0.0153812031947;
                            } else {
                                if (fs[53] <= -976.5) {
                                    if (fs[2] <= 2.5) {
                                        return -0.0063466115752;
                                    } else {
                                        return -0.0276143248693;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return -0.00239480886291;
                                    } else {
                                        return -0.0157127089986;
                                    }
                                }
                            }
                        } else {
                            if (fs[72] <= 9998.5) {
                                if (fs[98] <= 1.0) {
                                    return 0.0273180816826;
                                } else {
                                    if (fs[49] <= -2.5) {
                                        return -0.011748383361;
                                    } else {
                                        return -0.000625685803188;
                                    }
                                }
                            } else {
                                if (fs[76] <= 150.0) {
                                    return -0.0338634794331;
                                } else {
                                    if (fs[0] <= 2.5) {
                                        return -0.0158710044292;
                                    } else {
                                        return -0.00391607826511;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                return 0.196267024277;
            }
        }
    }
}
