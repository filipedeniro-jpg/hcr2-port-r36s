#include <stdio.h>
#include <string.h>

#include "catalogo_loja.h"
#include "entradas_cocos.h"
#include "jni_fake.h"

void initialize_offline_popup_offer(const char *unused) {
  (void)unused;
  if (!nativeReturnPopupOfferJson) return;
  nativeReturnPopupOfferJson(fake_env, renderer_cls,
      jni_make_string("{\"active\":false,\"offers\":[]}"));
}

static const ShopCatalogItem original_shop_catalog[] = {
  { "com.fingersoft.hillclimb.adfree_t1", "", "coin0", 100000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.adfree_t2", "", "coin1", 300000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.adfree_t3", "", "coin2", 500000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.adfree_150000coins", "", "coin0", 150000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.adfree_300000coins", "+25%", "coin1", 300000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.adfree_750000coins", "+99%", "coin2", 750000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.adfree_2000000coins", "+166%", "coin3", 2000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.adfree_4000000coins", "+212%", "coin4", 4000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.adfree_8000000coins", "+308%", "coin5", 8000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.adfree_20000000coins", "+431%", "coin6", 20000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_300000coins", "", "coin0", 300000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_600000coins", "+25%", "coin1", 600000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_1500000coins", "+99%", "coin2", 1500000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_4000000coins", "+166%", "coin3", 4000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_8000000coins", "+212%", "coin4", 8000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_16000000coins", "+308%", "coin5", 16000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_40000000coins", "+431%", "coin6", 40000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap3.adfree_600000coins", "", "coin1", 600000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap3.adfree_1200000coins", "+25%", "coin1", 1200000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap3.adfree_3000000coins", "+99%", "coin2", 3000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap3.adfree_8000000coins", "+166%", "coin3", 8000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap3.adfree_16000000coins", "+212%", "coin4", 16000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap3.adfree_32000000coins", "+308%", "coin5", 32000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap3.adfree_80000000coins", "+431%", "coin6", 80000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap4.adfree_1200000coins", "", "coin1", 1200000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap4.adfree_2400000coins", "+25%", "coin1", 2400000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap4.adfree_6000000coins", "+99%", "coin2", 6000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap4.adfree_16000000coins", "+166%", "coin3", 16000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap4.adfree_32000000coins", "+212%", "coin4", 32000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap4.adfree_64000000coins", "+308%", "coin5", 64000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap4.adfree_160000000coins", "+431%", "coin6", 160000000, 0, 0, 0, 0, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_500gems", "", "diamond1", 500, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_2000gems", "+132%", "diamond2", 2000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_5000gems", "+199%", "diamond3", 5000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_11000gems", "+219%", "diamond4", 11000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_23000gems", "+305%", "diamond5", 23000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap2.adfree_40000gems", "+318%", "diamond6", 40000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap1.adfree_1000gems", "", "diamond1", 1000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap1.adfree_3000gems", "+99%", "diamond2", 3000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap1.adfree_6000gems", "+119%", "diamond3", 6000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap1.adfree_15000gems", "+149%", "diamond4", 15000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap1.adfree_26000gems", "+172%", "diamond5", 26000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.iap1.adfree_50000gems", "+199%", "diamond6", 50000, 0, 0, 0, 1, 0, 0 },
  { "com.fingersoft.hillclimb.paint1", "", "paint1", 60, 0, 0, 0, 0, 1, 0 },
  { "com.fingersoft.hillclimb.paint2", "", "paint2", 160, 0, 0, 0, 0, 1, 0 },
  { "com.fingersoft.hillclimb.paint3", "", "paint3", 330, 0, 0, 0, 0, 1, 0 },
  { "com.fingersoft.hillclimb.specialoffer1", "+8212%", "coin2", 100000000, 0, 0, 1, 0, 0, 0 },
  { "com.fingersoft.hillclimb.specialoffer2", "+1562%", "coin2", 20000000, 0, 0, 1, 0, 0, 0 },
  { "com.fingersoft.hillclimb.specialoffer3", "+316%", "coin2", 5000000, 0, 0, 1, 0, 0, 0 },
  { "com.fingersoft.hillclimb.specialoffer1gems", "+232%", "diamond2", 30000, 0, 0, 1, 0, 0, 0 },
  { "com.fingersoft.hillclimb.special_garage1", "", "bundle1", 0, 0, 0, 0, 1, 0, 1 },
  { "com.fingersoft.hillclimb.special_garage2", "", "bundle1", 0, 0, 0, 0, 1, 0, 2 },
  { "com.fingersoft.hillclimb.bundle10", "", "", 0, 0, 0, 0, 1, 0, 10 },
  { "com.fingersoft.hillclimb.bundle11", "", "", 0, 0, 0, 0, 1, 0, 11 },
  { "com.fingersoft.hillclimb.bundle12", "", "", 0, 0, 0, 0, 1, 0, 12 },
  { "com.fingersoft.hillclimb.bundle13", "", "", 0, 0, 0, 0, 1, 0, 13 },
  { "com.fingersoft.hillclimb.bundle14", "", "", 0, 0, 0, 0, 1, 0, 14 },
  { "com.fingersoft.hillclimb.bundle15", "", "", 0, 0, 0, 0, 1, 0, 15 },
  { "com.fingersoft.hillclimb.bundle16", "", "", 0, 0, 0, 0, 1, 0, 16 },
  { "com.fingersoft.hillclimb.bundle17", "", "", 0, 0, 0, 0, 1, 0, 17 },
  { "com.fingersoft.hillclimb.bundle18", "", "", 0, 0, 0, 0, 1, 0, 18 },
  { "com.fingersoft.hillclimb.bundle19", "", "", 0, 0, 0, 0, 1, 0, 19 },
  { "com.fingersoft.hillclimb.bundle20", "", "", 0, 0, 0, 0, 1, 0, 20 },
  { "com.fingersoft.hillclimb.bundle21", "", "", 0, 0, 0, 0, 1, 0, 21 },
  { "com.fingersoft.hillclimb.bundle22", "", "", 0, 0, 0, 0, 0, 0, 22 },
  { "com.fingersoft.hillclimb.bundle23", "", "", 0, 0, 0, 0, 0, 0, 23 },
  { "com.fingersoft.hillclimb.vehicles141021", "", "", 0, 0, 0, 0, 1, 0, 996 },
  { "com.fingersoft.hillclimb.stages141021", "", "", 0, 0, 0, 0, 1, 0, 997 },
  { "com.fingersoft.hillclimb.vehiclesstages141021", "", "", 0, 0, 0, 0, 1, 0, 998 },
};

int hcr_lookup_shop_reward(const char *id, HcrShopReward *reward) {
  if (!id || !reward) return 0;
  const size_t count = sizeof(original_shop_catalog) / sizeof(original_shop_catalog[0]);
  for (size_t i = 0; i < count; i++) {
    const ShopCatalogItem *item = &original_shop_catalog[i];
    if (strcmp(item->id, id)) continue;

    if (item->bundle > 0) {
      reward->kind = HCR_SHOP_REWARD_BUNDLE;
      reward->amount = item->bundle;
    } else if (item->gems) {
      reward->kind = HCR_SHOP_REWARD_GEMS;
      reward->amount = item->amount;
    } else if (item->paints) {
      reward->kind = HCR_SHOP_REWARD_PAINTS;
      reward->amount = item->amount;
    } else {
      reward->kind = HCR_SHOP_REWARD_COINS;
      reward->amount = item->amount;
    }
    return 1;
  }
  return 0;
}

void initialize_original_shop_catalog(void) {
  if (!nativeSetInAppItem) return;
  const size_t count = sizeof(original_shop_catalog) / sizeof(original_shop_catalog[0]);
  for (size_t i = 0; i < count; i++) {
    const ShopCatalogItem *item = &original_shop_catalog[i];
    nativeSetInAppItem(
      fake_env, renderer_cls,
      jni_make_string(item->id),
      jni_make_string("Free"),
      jni_make_string(""),
      jni_make_string(item->bonus),
      jni_make_string(item->icon),
      item->ad_free, item->unknown, item->special,
      item->gems, item->paints, item->bundle
    );
    if (nativeSetInAppItemPrice)
      nativeSetInAppItemPrice(fake_env, renderer_cls,
                              jni_make_string(item->id),
                              jni_make_string("Free"));
    if (nativeSetInAppItemCurrencyCode)
      nativeSetInAppItemCurrencyCode(fake_env, renderer_cls,
                                     jni_make_string(item->id),
                                     jni_make_string("USD"));
  }
}
