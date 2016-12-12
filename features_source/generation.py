def get_new_features_dict(old_features_dict):
    result = {}
    for name in old_features_dict:
        if name in NOT_FEATURES:
            continue

        if name in CAT_FEATURES:
            found = False
            for cat in CAT_FEATURES[name]:
                if old_features_dict[name] == cat:
                    result[name + '=' + cat] = 1
                    found = True
                else:
                    result[name + '=' + cat] = 0
            result[name + '=OTHER'] = 0 if found else 1
        elif name in FLOAT_FEATURES:
            if old_features_dict[name] == 'UNDEFINED' or old_features_dict[name] == 'NaN':
                result[name + '=UNDEFINED'] = 1
                result[name] = FLOAT_FEATURES[name]
            else:

                result[name + '=UNDEFINED'] = 0
                result[name] = float(old_features_dict[name]) if float(old_features_dict[name]) < 10 ** 10 else 10 ** 10
        elif name in BIN_FEATURES:
            try:
                result[name] = float(BIN_FEATURES[name][old_features_dict[name]])
                result[name + '=UNDEFINED'] = 0
            except KeyError:
                result[name] = BIN_FEATURES[name]['default']
                result[name + '=UNDEFINED'] = 1
    return result
