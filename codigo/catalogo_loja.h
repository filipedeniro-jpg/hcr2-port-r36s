#ifndef CATALOGO_LOJA_H
#define CATALOGO_LOJA_H

#include <stdint.h>
#include <stddef.h>
#include "installer.h"
#include "jni_fake.h"

// Estrutura de item do catálogo de compras da loja original
typedef struct {
  const char *id;
  const char *bonus;
  const char *icon;
  int amount;
  uint8_t ad_free;
  uint8_t unknown;
  uint8_t special;
  uint8_t gems;
  uint8_t paints;
  int bundle;
} ShopCatalogItem;

// Inicializa a oferta popup offline informando array vazio
void initialize_offline_popup_offer(const char *unused);

// Localiza recompensa de pacote ou item no catálogo da loja
int hcr_lookup_shop_reward(const char *id, HcrShopReward *reward);

// Inicializa o catálogo de itens e preços da loja offline
void initialize_original_shop_catalog(void);

#endif // CATALOGO_LOJA_H
