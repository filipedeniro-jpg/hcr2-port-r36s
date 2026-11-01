#include <stdio.h>
#include <string.h>

#include "jni_fake.h"
#include "jni_iap_billing.h"

typedef struct {
  int coins, gems, paints, adfree, bundle, error;
  char product[160];
} HcrIapPending;

static HcrIapPending freeshop_pending;

void freeshop_reset_pending(void) {
  memset(&freeshop_pending, 0, sizeof(freeshop_pending));
}

int freeshop_get_coins(void) { return freeshop_pending.coins; }
int freeshop_get_gems(void) { return freeshop_pending.gems; }
int freeshop_get_paints(void) { return freeshop_pending.paints; }
int freeshop_get_adfree(void) { return freeshop_pending.adfree; }
int freeshop_get_bundle(void) { return freeshop_pending.bundle; }
int freeshop_get_error(void) { return freeshop_pending.error; }
const char *freeshop_get_product(void) { return freeshop_pending.product; }

void freeshop_apply_purchase(const char *product_id) {
  HcrShopReward reward = { HCR_SHOP_REWARD_NONE, 0 };
  if (!hcr_lookup_shop_reward(product_id, &reward)) return;
  if (freeshop_pending.product[0]) return;

  freeshop_pending.adfree = 1;
  switch (reward.kind) {
    case HCR_SHOP_REWARD_COINS:  freeshop_pending.coins += reward.amount; break;
    case HCR_SHOP_REWARD_GEMS:   freeshop_pending.gems += reward.amount; break;
    case HCR_SHOP_REWARD_PAINTS: freeshop_pending.paints += reward.amount; break;
    case HCR_SHOP_REWARD_BUNDLE:
      if (reward.amount > freeshop_pending.bundle) freeshop_pending.bundle = reward.amount;
      break;
    case HCR_SHOP_REWARD_NONE:
    default: break;
  }
  freeshop_pending.error = 0;
  snprintf(freeshop_pending.product, sizeof(freeshop_pending.product), "%s", product_id);
}

void freeshop_add_cheat_money(void) {
  freeshop_pending.coins += 100000;
  freeshop_pending.gems += 5000;
  freeshop_pending.adfree = 1;
  freeshop_pending.error = 0;
  snprintf(freeshop_pending.product, sizeof(freeshop_pending.product), "cheat_100k");
}
