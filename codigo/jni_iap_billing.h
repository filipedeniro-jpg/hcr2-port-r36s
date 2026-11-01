#ifndef JNI_IAP_BILLING_H
#define JNI_IAP_BILLING_H

// Reseta o estado temporário de compra pendente
void freeshop_reset_pending(void);

// Aplica compra imediata de um produto do catálogo offline
void freeshop_apply_purchase(const char *product_id);

// Adiciona moedas e gemas de teste/cheat ao jogador
void freeshop_add_cheat_money(void);

// Getters de estado de compras pendentes para callbacks do Cocos2d-x
int freeshop_get_coins(void);
int freeshop_get_gems(void);
int freeshop_get_paints(void);
int freeshop_get_adfree(void);
int freeshop_get_bundle(void);
int freeshop_get_error(void);
const char *freeshop_get_product(void);

#endif // JNI_IAP_BILLING_H
