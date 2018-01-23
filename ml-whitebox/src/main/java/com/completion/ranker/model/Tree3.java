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

public class Tree3 {
    public double calcTree(double... fs) {
        if (fs[0] <= 0.5) {
            if (fs[78] <= 0.5) {
                if (fs[2] <= 1.5) {
                    if (fs[4] <= 6.5) {
                        if (fs[34] <= 0.5) {
                            if (fs[53] <= -1138.5) {
                                if (fs[60] <= 0.5) {
                                    if (fs[40] <= 0.5) {
                                        return 0.615728397675;
                                    } else {
                                        return 0.670349819232;
                                    }
                                } else {
                                    if (fs[4] <= 4.5) {
                                        return 0.593347184954;
                                    } else {
                                        return 0.328919410869;
                                    }
                                }
                            } else {
                                if (fs[18] <= 0.5) {
                                    if (fs[33] <= 0.5) {
                                        return 0.626369580491;
                                    } else {
                                        return 0.766414165178;
                                    }
                                } else {
                                    if (fs[52] <= 0.5) {
                                        return 0.695232578841;
                                    } else {
                                        return 0.079641341053;
                                    }
                                }
                            }
                        } else {
                            return 0.75553829485;
                        }
                    } else {
                        if (fs[53] <= -1138.0) {
                            if (fs[62] <= -0.5) {
                                return 0.744795030414;
                            } else {
                                if (fs[71] <= 0.5) {
                                    return 0.4505544095;
                                } else {
                                    if (fs[94] <= 0.5) {
                                        return 0.564783160369;
                                    } else {
                                        return 0.36162594461;
                                    }
                                }
                            }
                        } else {
                            if (fs[59] <= 0.5) {
                                if (fs[18] <= 0.5) {
                                    if (fs[76] <= 150.0) {
                                        return 0.770984975995;
                                    } else {
                                        return 0.600013093302;
                                    }
                                } else {
                                    return 0.0738327757346;
                                }
                            } else {
                                return 0.765551687781;
                            }
                        }
                    }
                } else {
                    if (fs[72] <= 9998.5) {
                        if (fs[40] <= 0.5) {
                            if (fs[4] <= 18.5) {
                                if (fs[30] <= 0.5) {
                                    if (fs[4] <= 10.5) {
                                        return 0.803480141306;
                                    } else {
                                        return 0.615191685506;
                                    }
                                } else {
                                    return 0.514821447739;
                                }
                            } else {
                                return 0.249057452298;
                            }
                        } else {
                            if (fs[60] <= 0.5) {
                                if (fs[2] <= 2.5) {
                                    if (fs[53] <= -1138.0) {
                                        return 0.667349428543;
                                    } else {
                                        return 0.74529379786;
                                    }
                                } else {
                                    return 0.722740117863;
                                }
                            } else {
                                if (fs[2] <= 5.5) {
                                    if (fs[11] <= 0.5) {
                                        return 0.329207036741;
                                    } else {
                                        return 0.390005904549;
                                    }
                                } else {
                                    return -0.00345113166234;
                                }
                            }
                        }
                    } else {
                        if (fs[4] <= 9.5) {
                            if (fs[12] <= 0.5) {
                                if (fs[14] <= 0.5) {
                                    return 0.79650161545;
                                } else {
                                    return 0.824842676733;
                                }
                            } else {
                                return 0.744034806941;
                            }
                        } else {
                            return 0.0112911123748;
                        }
                    }
                }
            } else {
                if (fs[31] <= 0.5) {
                    if (fs[76] <= 25.0) {
                        if (fs[86] <= 0.5) {
                            if (fs[81] <= 0.5) {
                                if (fs[72] <= 9465.5) {
                                    if (fs[98] <= 0.5) {
                                        return 0.0899799646472;
                                    } else {
                                        return 0.29746101956;
                                    }
                                } else {
                                    if (fs[25] <= 0.5) {
                                        return 0.582122043589;
                                    } else {
                                        return 0.359594674227;
                                    }
                                }
                            } else {
                                if (fs[4] <= 7.5) {
                                    if (fs[23] <= 0.5) {
                                        return 0.506485520362;
                                    } else {
                                        return 0.826683827463;
                                    }
                                } else {
                                    if (fs[2] <= 4.5) {
                                        return 0.337155463409;
                                    } else {
                                        return 0.542596231516;
                                    }
                                }
                            }
                        } else {
                            if (fs[4] <= 2.5) {
                                if (fs[74] <= 0.5) {
                                    if (fs[4] <= 1.5) {
                                        return 0.773013546355;
                                    } else {
                                        return 0.724449661729;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return 0.127719339253;
                                    } else {
                                        return -0.0310773708014;
                                    }
                                }
                            } else {
                                if (fs[6] <= 0.5) {
                                    if (fs[2] <= 3.5) {
                                        return 0.746996332135;
                                    } else {
                                        return 0.809191344822;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return 0.399449540755;
                                    } else {
                                        return 0.628794623466;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[4] <= 10.5) {
                            if (fs[85] <= 6.5) {
                                if (fs[2] <= 1.5) {
                                    if (fs[85] <= 5.5) {
                                        return 0.483914502935;
                                    } else {
                                        return 0.146663071544;
                                    }
                                } else {
                                    if (fs[40] <= 0.5) {
                                        return 0.647428995979;
                                    } else {
                                        return 0.295153224709;
                                    }
                                }
                            } else {
                                if (fs[71] <= 0.5) {
                                    if (fs[89] <= 0.5) {
                                        return 0.642738199064;
                                    } else {
                                        return 0.762864503224;
                                    }
                                } else {
                                    if (fs[2] <= 1.5) {
                                        return 0.318820217172;
                                    } else {
                                        return 0.615877261515;
                                    }
                                }
                            }
                        } else {
                            if (fs[12] <= 0.5) {
                                if (fs[85] <= 5.5) {
                                    if (fs[40] <= 0.5) {
                                        return 0.439947725651;
                                    } else {
                                        return 0.203711620096;
                                    }
                                } else {
                                    if (fs[4] <= 11.5) {
                                        return 0.53257650332;
                                    } else {
                                        return 0.0814564406917;
                                    }
                                }
                            } else {
                                if (fs[43] <= 0.5) {
                                    if (fs[85] <= 5.5) {
                                        return 0.559883452344;
                                    } else {
                                        return 0.247575105223;
                                    }
                                } else {
                                    if (fs[60] <= 0.5) {
                                        return 0.794447147725;
                                    } else {
                                        return 0.682802474446;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[40] <= 0.5) {
                        if (fs[2] <= 1.5) {
                            if (fs[52] <= 0.5) {
                                if (fs[12] <= 0.5) {
                                    if (fs[76] <= 150.0) {
                                        return 0.554055348172;
                                    } else {
                                        return 0.674423551572;
                                    }
                                } else {
                                    if (fs[76] <= 150.0) {
                                        return 0.369056558607;
                                    } else {
                                        return 0.744991651846;
                                    }
                                }
                            } else {
                                if (fs[99] <= 0.5) {
                                    if (fs[53] <= -1138.5) {
                                        return 0.558001010403;
                                    } else {
                                        return 0.705254777681;
                                    }
                                } else {
                                    return 0.735754726185;
                                }
                            }
                        } else {
                            if (fs[4] <= 10.5) {
                                if (fs[2] <= 2.5) {
                                    if (fs[11] <= 0.5) {
                                        return 0.771491559513;
                                    } else {
                                        return 0.746229479221;
                                    }
                                } else {
                                    if (fs[60] <= 0.5) {
                                        return 0.732936135906;
                                    } else {
                                        return 0.781698302171;
                                    }
                                }
                            } else {
                                if (fs[100] <= 1.0) {
                                    if (fs[98] <= 1.5) {
                                        return 0.300607433397;
                                    } else {
                                        return 0.492964658286;
                                    }
                                } else {
                                    if (fs[11] <= 0.5) {
                                        return 0.734911420421;
                                    } else {
                                        return 0.669786906673;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[94] <= 0.5) {
                            if (fs[4] <= 6.0) {
                                return 0.370655415545;
                            } else {
                                return 0.810109322915;
                            }
                        } else {
                            if (fs[53] <= -1138.0) {
                                if (fs[11] <= 0.5) {
                                    if (fs[2] <= 1.5) {
                                        return 0.307493418863;
                                    } else {
                                        return 0.358235670856;
                                    }
                                } else {
                                    if (fs[4] <= 3.5) {
                                        return 0.184028044435;
                                    } else {
                                        return 0.389053931321;
                                    }
                                }
                            } else {
                                if (fs[53] <= -1128.0) {
                                    if (fs[4] <= 4.5) {
                                        return 0.328764094023;
                                    } else {
                                        return 0.0483461410883;
                                    }
                                } else {
                                    return 0.365489616012;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (fs[78] <= 0.5) {
                if (fs[4] <= 7.5) {
                    if (fs[59] <= 0.5) {
                        if (fs[4] <= 2.5) {
                            return 0.352129423261;
                        } else {
                            if (fs[34] <= 0.5) {
                                if (fs[30] <= 0.5) {
                                    if (fs[0] <= 1.5) {
                                        return 0.14671793094;
                                    } else {
                                        return 0.0230824210353;
                                    }
                                } else {
                                    if (fs[0] <= 2.5) {
                                        return -0.0631354023127;
                                    } else {
                                        return -0.05249724998;
                                    }
                                }
                            } else {
                                if (fs[4] <= 5.5) {
                                    return 0.51153314502;
                                } else {
                                    if (fs[0] <= 1.5) {
                                        return 0.704517592486;
                                    } else {
                                        return 0.759776504327;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[7] <= 0.5) {
                            if (fs[53] <= -556.5) {
                                if (fs[4] <= 5.5) {
                                    if (fs[2] <= 1.5) {
                                        return 0.0490469726015;
                                    } else {
                                        return 0.790051833592;
                                    }
                                } else {
                                    if (fs[0] <= 1.5) {
                                        return 0.804945080571;
                                    } else {
                                        return 0.418297794772;
                                    }
                                }
                            } else {
                                if (fs[0] <= 1.5) {
                                    return 0.221078847545;
                                } else {
                                    if (fs[0] <= 2.5) {
                                        return -0.0658356715638;
                                    } else {
                                        return -0.0238306060921;
                                    }
                                }
                            }
                        } else {
                            return -0.0635477509664;
                        }
                    }
                } else {
                    if (fs[18] <= 0.5) {
                        if (fs[4] <= 11.5) {
                            if (fs[53] <= -1118.0) {
                                if (fs[76] <= 150.0) {
                                    if (fs[4] <= 8.5) {
                                        return -0.0352660241013;
                                    } else {
                                        return -0.00588887477835;
                                    }
                                } else {
                                    if (fs[12] <= 0.5) {
                                        return 0.0735631149598;
                                    } else {
                                        return 0.150316261887;
                                    }
                                }
                            } else {
                                if (fs[70] <= -1.5) {
                                    if (fs[47] <= 0.5) {
                                        return -0.0403674069103;
                                    } else {
                                        return -0.0471811911725;
                                    }
                                } else {
                                    if (fs[60] <= 0.5) {
                                        return -0.0214254316578;
                                    } else {
                                        return -0.0486543339085;
                                    }
                                }
                            }
                        } else {
                            if (fs[12] <= 0.5) {
                                if (fs[52] <= 0.5) {
                                    if (fs[53] <= -1138.0) {
                                        return -0.0556555783275;
                                    } else {
                                        return 0.0425978386697;
                                    }
                                } else {
                                    if (fs[94] <= 0.5) {
                                        return -0.0453681701781;
                                    } else {
                                        return 6.92377766501e-05;
                                    }
                                }
                            } else {
                                return 0.236414030017;
                            }
                        }
                    } else {
                        if (fs[2] <= 2.5) {
                            if (fs[11] <= 0.5) {
                                if (fs[72] <= 9998.5) {
                                    if (fs[72] <= 9992.5) {
                                        return 0.0588070169886;
                                    } else {
                                        return 0.0364589055082;
                                    }
                                } else {
                                    return -0.0584149529691;
                                }
                            } else {
                                if (fs[0] <= 7.5) {
                                    if (fs[72] <= 9997.5) {
                                        return 0.0505859314895;
                                    } else {
                                        return -0.0216129392178;
                                    }
                                } else {
                                    return -0.0502250312345;
                                }
                            }
                        } else {
                            if (fs[12] <= 0.5) {
                                return 0.339216230211;
                            } else {
                                if (fs[0] <= 2.5) {
                                    return 0.191427126696;
                                } else {
                                    return 0.0541758535023;
                                }
                            }
                        }
                    }
                }
            } else {
                if (fs[72] <= 9790.5) {
                    if (fs[0] <= 1.5) {
                        if (fs[98] <= 0.5) {
                            if (fs[40] <= 0.5) {
                                if (fs[12] <= 0.5) {
                                    if (fs[76] <= 25.0) {
                                        return -0.0152685292409;
                                    } else {
                                        return 0.0524748844814;
                                    }
                                } else {
                                    if (fs[92] <= 0.5) {
                                        return 0.0253087532331;
                                    } else {
                                        return 0.576408109904;
                                    }
                                }
                            } else {
                                if (fs[85] <= 7.5) {
                                    if (fs[76] <= 75.0) {
                                        return 0.00712923670383;
                                    } else {
                                        return 0.337462588643;
                                    }
                                } else {
                                    if (fs[59] <= 0.5) {
                                        return 0.305645726005;
                                    } else {
                                        return 0.811034698377;
                                    }
                                }
                            }
                        } else {
                            if (fs[47] <= 0.5) {
                                if (fs[11] <= 0.5) {
                                    if (fs[87] <= 0.5) {
                                        return 0.109663408263;
                                    } else {
                                        return 0.217581125349;
                                    }
                                } else {
                                    if (fs[4] <= 7.5) {
                                        return 0.143942752128;
                                    } else {
                                        return 0.0725373038255;
                                    }
                                }
                            } else {
                                if (fs[68] <= 1.5) {
                                    if (fs[76] <= 25.0) {
                                        return -0.00866535135241;
                                    } else {
                                        return -0.0454415452767;
                                    }
                                } else {
                                    return 0.324389499432;
                                }
                            }
                        }
                    } else {
                        if (fs[87] <= 0.5) {
                            if (fs[33] <= 0.5) {
                                if (fs[0] <= 5.5) {
                                    if (fs[76] <= 25.0) {
                                        return -0.0377939864087;
                                    } else {
                                        return -0.0172664877589;
                                    }
                                } else {
                                    if (fs[53] <= -1473.5) {
                                        return -0.0426623015642;
                                    } else {
                                        return -0.0444546018198;
                                    }
                                }
                            } else {
                                if (fs[11] <= 0.5) {
                                    if (fs[47] <= 0.5) {
                                        return -0.0187561967948;
                                    } else {
                                        return -0.0446528460359;
                                    }
                                } else {
                                    if (fs[47] <= 0.5) {
                                        return -0.0355002830524;
                                    } else {
                                        return -0.0451153937775;
                                    }
                                }
                            }
                        } else {
                            if (fs[81] <= 0.5) {
                                if (fs[0] <= 6.5) {
                                    if (fs[12] <= 0.5) {
                                        return 0.0630492883936;
                                    } else {
                                        return 0.218192482395;
                                    }
                                } else {
                                    if (fs[53] <= -1428.0) {
                                        return 0.0432712410941;
                                    } else {
                                        return -0.0439089695629;
                                    }
                                }
                            } else {
                                if (fs[47] <= 0.5) {
                                    if (fs[100] <= 1.5) {
                                        return -0.00527440245848;
                                    } else {
                                        return 0.0694686329061;
                                    }
                                } else {
                                    if (fs[94] <= 0.5) {
                                        return -0.0417315514275;
                                    } else {
                                        return -0.0455831948888;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (fs[53] <= -1072.5) {
                        if (fs[0] <= 1.5) {
                            if (fs[85] <= 7.5) {
                                if (fs[12] <= 0.5) {
                                    if (fs[72] <= 9999.5) {
                                        return 0.142869615503;
                                    } else {
                                        return 0.293512443969;
                                    }
                                } else {
                                    if (fs[25] <= 0.5) {
                                        return 0.203394770973;
                                    } else {
                                        return 0.325192790819;
                                    }
                                }
                            } else {
                                if (fs[2] <= 1.5) {
                                    if (fs[4] <= 7.5) {
                                        return 0.312324424097;
                                    } else {
                                        return -0.0256486588278;
                                    }
                                } else {
                                    if (fs[60] <= 0.5) {
                                        return 0.810760122447;
                                    } else {
                                        return 0.608985983583;
                                    }
                                }
                            }
                        } else {
                            if (fs[4] <= 12.5) {
                                if (fs[85] <= 6.5) {
                                    if (fs[72] <= 9988.5) {
                                        return 0.0270654406711;
                                    } else {
                                        return 0.154052825999;
                                    }
                                } else {
                                    if (fs[60] <= 0.5) {
                                        return 0.288948181806;
                                    } else {
                                        return 0.137599787876;
                                    }
                                }
                            } else {
                                if (fs[11] <= 0.5) {
                                    if (fs[94] <= 0.5) {
                                        return 0.0687594823838;
                                    } else {
                                        return 0.231139799076;
                                    }
                                } else {
                                    if (fs[43] <= 0.5) {
                                        return 0.0177169765186;
                                    } else {
                                        return 0.0893940742721;
                                    }
                                }
                            }
                        }
                    } else {
                        if (fs[25] <= 0.5) {
                            if (fs[53] <= 3.5) {
                                if (fs[100] <= 0.5) {
                                    if (fs[57] <= 0.5) {
                                        return 0.0416753545298;
                                    } else {
                                        return 0.635199530994;
                                    }
                                } else {
                                    if (fs[4] <= 6.5) {
                                        return 0.0470578671368;
                                    } else {
                                        return -0.0188200017736;
                                    }
                                }
                            } else {
                                if (fs[76] <= 150.0) {
                                    if (fs[96] <= 0.5) {
                                        return -0.0496080763411;
                                    } else {
                                        return -0.0449839091765;
                                    }
                                } else {
                                    if (fs[0] <= 2.5) {
                                        return -0.0440138139889;
                                    } else {
                                        return -0.0454207220931;
                                    }
                                }
                            }
                        } else {
                            if (fs[18] <= 0.5) {
                                if (fs[72] <= 9999.5) {
                                    if (fs[2] <= 3.5) {
                                        return -0.0466239517231;
                                    } else {
                                        return -0.0363011841376;
                                    }
                                } else {
                                    if (fs[0] <= 9.5) {
                                        return -0.0210779943049;
                                    } else {
                                        return -0.0415047362621;
                                    }
                                }
                            } else {
                                if (fs[0] <= 1.5) {
                                    return 0.230049875803;
                                } else {
                                    if (fs[72] <= 9998.5) {
                                        return -0.0468966520154;
                                    } else {
                                        return -9.73829605131e-05;
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
