
### Arquivo `asset_shim.c` (Total: 182 linhas)
- [hcr1/codigo/asset_shim.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.c#L1-L45) - Definições e suporte
- [hcr1/codigo/asset_shim.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.c#L46-L90) - Funções: asset_path
- [hcr1/codigo/asset_shim.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.c#L91-L135) - Funções: AAsset_read_fake, AAsset_seek_fake, AAsset_close_fake, AAsset_getLength_fake, AAsset_getLength64_fake, AAsset_openFileDescriptor64_fake
- [hcr1/codigo/asset_shim.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.c#L136-L180) - Funções: AAssetDir_close_fake
- [hcr1/codigo/asset_shim.c #L181-L182](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.c#L181-L182) - Definições e suporte

### Arquivo `asset_shim.h` (Total: 34 linhas)
- [hcr1/codigo/asset_shim.h #L1-L34](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.h#L1-L34) - Definições e suporte

### Arquivo `config.c` (Total: 130 linhas)
- [hcr1/codigo/config.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/config.c#L1-L45) - Funções: parse_var, read_config
- [hcr1/codigo/config.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/config.c#L46-L90) - Definições e suporte
- [hcr1/codigo/config.c #L91-L130](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/config.c#L91-L130) - Funções: write_config

### Arquivo `config.h` (Total: 71 linhas)
- [hcr1/codigo/config.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/config.h#L1-L45) - Definições e suporte
- [hcr1/codigo/config.h #L46-L71](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/config.h#L46-L71) - Definições e suporte

### Arquivo `error.c` (Total: 18 linhas)
- [hcr1/codigo/error.c #L1-L18](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/error.c#L1-L18) - Funções: fatal_error

### Arquivo `error.h` (Total: 14 linhas)
- [hcr1/codigo/error.h #L1-L14](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/error.h#L1-L14) - Definições e suporte

### Arquivo `hcr_http.c` (Total: 58 linhas)
- [hcr1/codigo/hcr_http.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_http.c#L1-L45) - Funções: hcr_http_init, hcr_http_network_available, hcr_http_set_callbacks, hcr_http_enqueue_popup, hcr_http_enqueue_mission
- [hcr1/codigo/hcr_http.c #L46-L58](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_http.c#L46-L58) - Funções: hcr_http_pump, hcr_http_exit

### Arquivo `hcr_http.h` (Total: 14 linhas)
- [hcr1/codigo/hcr_http.h #L1-L14](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_http.h#L1-L14) - Definições e suporte

### Arquivo `hcr_log.c` (Total: 182 linhas)
- [hcr1/codigo/hcr_log.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.c#L1-L45) - Funções: hcr_time_us, write_line
- [hcr1/codigo/hcr_log.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.c#L46-L90) - Funções: hcr_log_init
- [hcr1/codigo/hcr_log.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.c#L91-L135) - Funções: hcr_logf
- [hcr1/codigo/hcr_log.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.c#L136-L180) - Funções: hcr_log_flush, hcr_log_shutdown
- [hcr1/codigo/hcr_log.c #L181-L182](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.c#L181-L182) - Definições e suporte

### Arquivo `hcr_log.h` (Total: 29 linhas)
- [hcr1/codigo/hcr_log.h #L1-L29](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.h#L1-L29) - Definições e suporte

### Arquivo `imports.c` (Total: 1009 linhas)
- [hcr1/codigo/imports.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L1-L45) - Definições e suporte
- [hcr1/codigo/imports.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L46-L90) - Funções: __stack_chk_fail_fake, ExecuteProgram_stub, android_log_print_fake
- [hcr1/codigo/imports.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L91-L135) - Funções: android_log_write_fake, android_log_assert_fake, dlclose_fake, rng_next, is_urandom, open_fake
- [hcr1/codigo/imports.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L136-L180) - Funções: read_fake, close_fake, __open_2_fake, __read_chk_fake
- [hcr1/codigo/imports.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L181-L225) - Funções: init_bionic_ctype, __ctype_get_mb_cur_max_fake, socket_fake, bind_fake, connect_fake, listen_fake
- [hcr1/codigo/imports.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L226-L270) - Funções: send_fake, recv_fake, getsockname_fake, getsockopt_fake, select_fake, isdigit_l_fake, islower_l_fake, isupper_l_fake, isxdigit_l_fake, iswalpha_l_fake, iswblank_l_fake, iswcntrl_l_fake, iswdigit_l_fake, iswlower_l_fake, iswprint_l_fake, iswpunct_l_fake, iswspace_l_fake, iswupper_l_fake, iswxdigit_l_fake, strcoll_l_fake, strftime_l_fake, strxfrm_l_fake, tolower_l_fake, toupper_l_fake
- [hcr1/codigo/imports.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L271-L315) - Funções: towlower_l_fake, towupper_l_fake, wcscoll_l_fake, wcsxfrm_l_fake, abort_fake, getpid_fake, sched_yield_fake, system_fake, setpriority_fake, getpriority_fake, pthread_attr_setschedpolicy_fake, pthread_getschedparam_fake, clock_nanosleep_fake, clock_gettime_fake, gettimeofday_fake, sincos_fake
- [hcr1/codigo/imports.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L316-L360) - Funções: sysconf_fake
- [hcr1/codigo/imports.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L361-L405) - Funções: bionic_mutex_destroy, pthread_mutex_init_fake, pthread_mutex_lock_fake
- [hcr1/codigo/imports.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L406-L450) - Funções: pthread_mutex_trylock_fake, pthread_mutex_unlock_fake, pthread_mutex_destroy_fake
- [hcr1/codigo/imports.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L451-L495) - Funções: bionic_cond_destroy, pthread_cond_init_fake, pthread_cond_signal_fake, pthread_cond_broadcast_fake
- [hcr1/codigo/imports.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L496-L540) - Funções: pthread_cond_destroy_fake, vpl_runtime_ready, cond_wait_slice, cond_wait_startup_tick, pthread_cond_wait_fake, pthread_cond_timedwait_fake
- [hcr1/codigo/imports.c #L541-L585](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L541-L585) - Funções: pthread_mutexattr_init_fake, pthread_mutexattr_settype_fake, pthread_mutexattr_destroy_fake, bionic_rwlock_destroy
- [hcr1/codigo/imports.c #L586-L630](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L586-L630) - Funções: pthread_rwlock_init_fake, pthread_rwlock_rdlock_fake, pthread_rwlock_tryrdlock_fake, pthread_rwlock_wrlock_fake, pthread_rwlock_trywrlock_fake, pthread_rwlock_unlock_fake
- [hcr1/codigo/imports.c #L631-L675](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L631-L675) - Funções: pthread_rwlock_destroy_fake
- [hcr1/codigo/imports.c #L676-L720](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L676-L720) - Funções: bionic_sem_destroy, sem_init_fake, sem_destroy_fake, sem_post_fake, sem_wait_fake
- [hcr1/codigo/imports.c #L721-L765](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L721-L765) - Funções: sem_trywait_fake, sem_timedwait_fake, pthread_detach_fake
- [hcr1/codigo/imports.c #L766-L810](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L766-L810) - Funções: resolve_egl_proc, glUnmapBufferOES_wrap, glBindFramebuffer_wrap, glViewport_wrap
- [hcr1/codigo/imports.c #L811-L855](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L811-L855) - Definições e suporte
- [hcr1/codigo/imports.c #L856-L900](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L856-L900) - Definições e suporte
- [hcr1/codigo/imports.c #L901-L945](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L901-L945) - Definições e suporte
- [hcr1/codigo/imports.c #L946-L990](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L946-L990) - Definições e suporte
- [hcr1/codigo/imports.c #L991-L1009](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L991-L1009) - Funções: vpl_resolve_imports

### Arquivo `imports.h` (Total: 20 linhas)
- [hcr1/codigo/imports.h #L1-L20](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.h#L1-L20) - Definições e suporte

### Arquivo `imports_cxx.h` (Total: 59 linhas)
- [hcr1/codigo/imports_cxx.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_cxx.h#L1-L45) - Definições e suporte
- [hcr1/codigo/imports_cxx.h #L46-L59](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_cxx.h#L46-L59) - Definições e suporte

### Arquivo `imports_direct.h` (Total: 233 linhas)
- [hcr1/codigo/imports_direct.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L1-L45) - Definições e suporte
- [hcr1/codigo/imports_direct.h #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L46-L90) - Definições e suporte
- [hcr1/codigo/imports_direct.h #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L91-L135) - Definições e suporte
- [hcr1/codigo/imports_direct.h #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L136-L180) - Definições e suporte
- [hcr1/codigo/imports_direct.h #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L181-L225) - Definições e suporte
- [hcr1/codigo/imports_direct.h #L226-L233](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L226-L233) - Definições e suporte

### Arquivo `installer.c` (Total: 25 linhas)
- [hcr1/codigo/installer.c #L1-L25](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/installer.c#L1-L25) - Funções: installer_prepare_game_files

### Arquivo `installer.h` (Total: 8 linhas)
- [hcr1/codigo/installer.h #L1-L8](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/installer.h#L1-L8) - Definições e suporte

### Arquivo `jni_fake.c` (Total: 1480 linhas)
- [hcr1/codigo/jni_fake.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1-L45) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L46-L90) - Funções: jni_set_popup_offer_callback, jni_set_bitmap_callback
- [hcr1/codigo/jni_fake.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L91-L135) - Funções: free_ref
- [hcr1/codigo/jni_fake.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L136-L180) - Funções: delete_local, jni_delete_local_ref, jni_input_array_set
- [hcr1/codigo/jni_fake.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L181-L225) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L226-L270) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L271-L315) - Funções: prefs_checksum, pref_key_hash, pref_find_nolock, pref_hash_insert_nolock, pref_hash_rebuild, prefs_free_array
- [hcr1/codigo/jni_fake.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L316-L360) - Funções: prefs_read_file, prefs_parse_v2
- [hcr1/codigo/jni_fake.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L361-L405) - Funções: prefs_parse_v1
- [hcr1/codigo/jni_fake.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L406-L450) - Funções: prefs_load_candidate, prefs_load
- [hcr1/codigo/jni_fake.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L451-L495) - Funções: prefs_serialize_locked
- [hcr1/codigo/jni_fake.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L496-L540) - Funções: prefs_validate_v2_file, prefs_write_transaction
- [hcr1/codigo/jni_fake.c #L541-L585](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L541-L585) - Funções: prefs_write_snapshot
- [hcr1/codigo/jni_fake.c #L586-L630](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L586-L630) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L631-L675](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L631-L675) - Funções: prefs_start_worker, prefs_apply_async, prefs_commit_sync
- [hcr1/codigo/jni_fake.c #L676-L720](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L676-L720) - Funções: pref_has, pref_set
- [hcr1/codigo/jni_fake.c #L721-L765](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L721-L765) - Funções: freeshop_reset_pending, freeshop_apply_purchase
- [hcr1/codigo/jni_fake.c #L766-L810](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L766-L810) - Funções: freeshop_add_cheat_money
- [hcr1/codigo/jni_fake.c #L811-L855](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L811-L855) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L856-L900](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L856-L900) - Funções: call_int
- [hcr1/codigo/jni_fake.c #L901-L945](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L901-L945) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L946-L990](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L946-L990) - Funções: call_long
- [hcr1/codigo/jni_fake.c #L991-L1035](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L991-L1035) - Funções: call_double, call_float, call_void
- [hcr1/codigo/jni_fake.c #L1036-L1080](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1036-L1080) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L1081-L1125](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1081-L1125) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L1126-L1170](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1126-L1170) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L1171-L1215](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1171-L1215) - Funções: jni_take_consent_form_request, j_GetVersion
- [hcr1/codigo/jni_fake.c #L1216-L1260](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1216-L1260) - Funções: j_DeleteGlobalRef, j_DeleteLocalRef, j_IsSameObject, j_EnsureLocalCapacity, j_PushLocalFrame
- [hcr1/codigo/jni_fake.c #L1261-L1305](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1261-L1305) - Funções: j_CallVoidMethod, j_CallVoidMethodV, j_ReleaseStringUTFChars, j_GetStringUTFLength, j_GetStringLength
- [hcr1/codigo/jni_fake.c #L1306-L1350](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1306-L1350) - Funções: j_ReleaseStringChars, j_GetArrayLength, j_ReleasePriArrayElements, j_GetPriArrayRegion, j_SetPriArrayRegion, j_RegisterNatives, j_GetJavaVM, j_ExceptionCheck, j_void1
- [hcr1/codigo/jni_fake.c #L1351-L1395](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1351-L1395) - Funções: j_unimplemented, vm_DestroyJavaVM, vm_AttachCurrentThread, vm_DetachCurrentThread, vm_GetEnv, jni_init
- [hcr1/codigo/jni_fake.c #L1396-L1440](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1396-L1440) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L1441-L1480](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1441-L1480) - Funções: jni_flush_prefs

### Arquivo `jni_fake.h` (Total: 62 linhas)
- [hcr1/codigo/jni_fake.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.h#L1-L45) - Definições e suporte
- [hcr1/codigo/jni_fake.h #L46-L62](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.h#L46-L62) - Definições e suporte

### Arquivo `libc_shim.c` (Total: 558 linhas)
- [hcr1/codigo/libc_shim.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L1-L45) - Definições e suporte
- [hcr1/codigo/libc_shim.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L46-L90) - Funções: __strlen_chk_fake, __vsnprintf_chk_fake, __vsprintf_chk_fake, __system_property_get_fake, getauxval_fake, gettid_fake, syscall_fake
- [hcr1/codigo/libc_shim.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L91-L135) - Funções: sincosf_fake, android_set_abort_message_fake, posix_memalign_fake
- [hcr1/codigo/libc_shim.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L136-L180) - Definições e suporte
- [hcr1/codigo/libc_shim.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L181-L225) - Funções: apk_tail_prepare
- [hcr1/codigo/libc_shim.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L226-L270) - Funções: forget_apk_stream, apk_cache_fill
- [hcr1/codigo/libc_shim.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L271-L315) - Definições e suporte
- [hcr1/codigo/libc_shim.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L316-L360) - Funções: is_fake_file
- [hcr1/codigo/libc_shim.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L361-L405) - Funções: fwrite_fake, fread_fake
- [hcr1/codigo/libc_shim.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L406-L450) - Funções: fputc_fake, fputs_fake, ferror_fake
- [hcr1/codigo/libc_shim.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L451-L495) - Funções: clearerr_fake, fileno_fake, fflush_fake, fclose_fake, fseek_fake
- [hcr1/codigo/libc_shim.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L496-L540) - Funções: ftell_fake, rewind_fake, fprintf_fake
- [hcr1/codigo/libc_shim.c #L541-L558](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L541-L558) - Funções: vfprintf_fake, getc_fake, ungetc_fake

### Arquivo `libc_shim.h` (Total: 56 linhas)
- [hcr1/codigo/libc_shim.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.h#L1-L45) - Definições e suporte
- [hcr1/codigo/libc_shim.h #L46-L56](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.h#L46-L56) - Definições e suporte

### Arquivo `main.c` (Total: 1337 linhas)
- [hcr1/codigo/main.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1-L45) - Funções: crash_handler
- [hcr1/codigo/main.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L46-L90) - Definições e suporte
- [hcr1/codigo/main.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L91-L135) - Funções: graceful_shutdown_handler
- [hcr1/codigo/main.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L136-L180) - Funções: install_signal_handlers, check_data, set_screen_size
- [hcr1/codigo/main.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L181-L225) - Funções: sdl_gl_init
- [hcr1/codigo/main.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L226-L270) - Funções: sdl_gl_deinit, load_module
- [hcr1/codigo/main.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L271-L315) - Funções: resolve_function, resolve_entry_points
- [hcr1/codigo/main.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L316-L360) - Funções: on_bitmap_create, initialize_offline_popup_offer
- [hcr1/codigo/main.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L361-L405) - Definições e suporte
- [hcr1/codigo/main.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L406-L450) - Definições e suporte
- [hcr1/codigo/main.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L451-L495) - Funções: hcr_lookup_shop_reward, initialize_original_shop_catalog
- [hcr1/codigo/main.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L496-L540) - Funções: patch_optional_daily_event_node, patch_all_stack_checks
- [hcr1/codigo/main.c #L541-L585](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L541-L585) - Funções: patch_eula_screen, patch_vehicle_select
- [hcr1/codigo/main.c #L586-L630](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L586-L630) - Definições e suporte
- [hcr1/codigo/main.c #L631-L675](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L631-L675) - Definições e suporte
- [hcr1/codigo/main.c #L676-L720](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L676-L720) - Funções: clampf
- [hcr1/codigo/main.c #L721-L765](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L721-L765) - Funções: update_input
- [hcr1/codigo/main.c #L766-L810](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L766-L810) - Definições e suporte
- [hcr1/codigo/main.c #L811-L855](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L811-L855) - Definições e suporte
- [hcr1/codigo/main.c #L856-L900](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L856-L900) - Definições e suporte
- [hcr1/codigo/main.c #L901-L945](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L901-L945) - Definições e suporte
- [hcr1/codigo/main.c #L946-L990](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L946-L990) - Definições e suporte
- [hcr1/codigo/main.c #L991-L1035](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L991-L1035) - Definições e suporte
- [hcr1/codigo/main.c #L1036-L1080](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1036-L1080) - Definições e suporte
- [hcr1/codigo/main.c #L1081-L1125](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1081-L1125) - Definições e suporte
- [hcr1/codigo/main.c #L1126-L1170](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1126-L1170) - Definições e suporte
- [hcr1/codigo/main.c #L1171-L1215](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1171-L1215) - Funções: main
- [hcr1/codigo/main.c #L1216-L1260](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1216-L1260) - Definições e suporte
- [hcr1/codigo/main.c #L1261-L1305](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1261-L1305) - Definições e suporte
- [hcr1/codigo/main.c #L1306-L1337](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1306-L1337) - Definições e suporte

### Arquivo `opensles.c` (Total: 1131 linhas)
- [hcr1/codigo/opensles.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L1-L45) - Definições e suporte
- [hcr1/codigo/opensles.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L46-L90) - Definições e suporte
- [hcr1/codigo/opensles.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L91-L135) - Definições e suporte
- [hcr1/codigo/opensles.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L136-L180) - Definições e suporte
- [hcr1/codigo/opensles.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L181-L225) - Definições e suporte
- [hcr1/codigo/opensles.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L226-L270) - Definições e suporte
- [hcr1/codigo/opensles.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L271-L315) - Funções: mb_to_linear, mix_player
- [hcr1/codigo/opensles.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L316-L360) - Funções: mix_cocos_voice
- [hcr1/codigo/opensles.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L361-L405) - Funções: mix_cocos_audio, audio_callback
- [hcr1/codigo/opensles.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L406-L450) - Funções: opensles_pump, ensure_device
- [hcr1/codigo/opensles.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L451-L495) - Definições e suporte
- [hcr1/codigo/opensles.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L496-L540) - Funções: cocos_preload_find_locked, cocos_attach_pending_sound
- [hcr1/codigo/opensles.c #L541-L585](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L541-L585) - Funções: cocos_preload_worker
- [hcr1/codigo/opensles.c #L586-L630](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L586-L630) - Funções: cocos_preload_start, cocos_audio_preload
- [hcr1/codigo/opensles.c #L631-L675](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L631-L675) - Funções: cocos_audio_play_effect
- [hcr1/codigo/opensles.c #L676-L720](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L676-L720) - Funções: cocos_audio_stop_effect, cocos_audio_pause_effect, cocos_audio_stop_all_effects, cocos_audio_pause_all_effects
- [hcr1/codigo/opensles.c #L721-L765](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L721-L765) - Funções: cocos_audio_set_effects_volume, cocos_audio_set_effect_volume, cocos_audio_set_effect_rate, cocos_audio_play_background
- [hcr1/codigo/opensles.c #L766-L810](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L766-L810) - Funções: cocos_audio_stop_background, cocos_audio_pause_background, cocos_audio_rewind_background, cocos_audio_background_playing, cocos_audio_set_background_volume, cocos_audio_background_volume, cocos_audio_effects_volume, bq_Enqueue
- [hcr1/codigo/opensles.c #L811-L855](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L811-L855) - Funções: bq_Clear, bq_GetState, bq_RegisterCallback
- [hcr1/codigo/opensles.c #L856-L900](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L856-L900) - Funções: play_SetPlayState, play_GetPlayState, play_ret0_u32, play_ok_u32, play_ok, play_RegisterCallback, vol_SetVolumeLevel, vol_GetVolumeLevel, vol_GetMaxVolumeLevel, vol_SetMute
- [hcr1/codigo/opensles.c #L901-L945](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L901-L945) - Funções: vol_GetMute, vol_enable, vol_isenabled, vol_setpos, vol_getpos, obj_Realize, obj_Resume, obj_GetState, obj_RegisterCallback, obj_Abort, obj_SetPriority, obj_GetPriority, obj_SetLOC, mix_GetInterface, simple_Destroy, player_GetInterface
- [hcr1/codigo/opensles.c #L946-L990](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L946-L990) - Funções: player_Destroy, eng_CreateAudioPlayer
- [hcr1/codigo/opensles.c #L991-L1035](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L991-L1035) - Funções: eng_CreateOutputMix
- [hcr1/codigo/opensles.c #L1036-L1080](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L1036-L1080) - Funções: eng_unsupported, engine_GetInterface
- [hcr1/codigo/opensles.c #L1081-L1125](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L1081-L1125) - Funções: opensles_shutdown
- [hcr1/codigo/opensles.c #L1126-L1131](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L1126-L1131) - Definições e suporte

### Arquivo `opensles.h` (Total: 64 linhas)
- [hcr1/codigo/opensles.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.h#L1-L45) - Definições e suporte
- [hcr1/codigo/opensles.h #L46-L64](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.h#L46-L64) - Definições e suporte

### Arquivo `overlay.c` (Total: 169 linhas)
- [hcr1/codigo/overlay.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay.c#L1-L45) - Funções: compile, lazy_init
- [hcr1/codigo/overlay.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay.c#L46-L90) - Funções: push_pt, push_tri, build_arrow
- [hcr1/codigo/overlay.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay.c#L91-L135) - Funções: overlay_draw_cursor
- [hcr1/codigo/overlay.c #L136-L169](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay.c#L136-L169) - Definições e suporte

### Arquivo `overlay.h` (Total: 11 linhas)
- [hcr1/codigo/overlay.h #L1-L11](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay.h#L1-L11) - Definições e suporte

### Arquivo `overlay_menu.c` (Total: 697 linhas)
- [hcr1/codigo/overlay_menu.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L1-L45) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L46-L90) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L91-L135) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L136-L180) - Funções: compile_shader, init_font_texture
- [hcr1/codigo/overlay_menu.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L181-L225) - Funções: overlay_menu_init
- [hcr1/codigo/overlay_menu.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L226-L270) - Funções: overlay_menu_is_open, overlay_menu_open, overlay_menu_close, overlay_menu_toggle
- [hcr1/codigo/overlay_menu.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L271-L315) - Funções: apply_remap, overlay_menu_handle_event
- [hcr1/codigo/overlay_menu.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L316-L360) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L361-L405) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L406-L450) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L451-L495) - Funções: draw_box, draw_string
- [hcr1/codigo/overlay_menu.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L496-L540) - Funções: overlay_menu_render
- [hcr1/codigo/overlay_menu.c #L541-L585](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L541-L585) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L586-L630](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L586-L630) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L631-L675](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L631-L675) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L676-L697](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L676-L697) - Definições e suporte

### Arquivo `overlay_menu.h` (Total: 27 linhas)
- [hcr1/codigo/overlay_menu.h #L1-L27](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.h#L1-L27) - Definições e suporte

### Arquivo `so_util.c` (Total: 405 linhas)
- [hcr1/codigo/so_util.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L1-L45) - Funções: so_flush_caches, so_free_temp
- [hcr1/codigo/so_util.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L46-L90) - Funções: so_finalize, so_load
- [hcr1/codigo/so_util.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L91-L135) - Definições e suporte
- [hcr1/codigo/so_util.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L136-L180) - Definições e suporte
- [hcr1/codigo/so_util.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L181-L225) - Funções: so_dynamic_tag, so_process_relr, so_relocate
- [hcr1/codigo/so_util.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L226-L270) - Definições e suporte
- [hcr1/codigo/so_util.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L271-L315) - Funções: so_lookup_export, so_resolve_symbol, so_resolve
- [hcr1/codigo/so_util.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L316-L360) - Funções: so_execute_init_array
- [hcr1/codigo/so_util.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L361-L405) - Funções: so_find_addr_rx, so_try_find_addr_rx

### Arquivo `so_util.h` (Total: 67 linhas)
- [hcr1/codigo/so_util.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.h#L1-L45) - Definições e suporte
- [hcr1/codigo/so_util.h #L46-L67](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.h#L46-L67) - Definições e suporte

### Arquivo `switch.h` (Total: 55 linhas)
- [hcr1/codigo/switch.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/switch.h#L1-L45) - Funções: armGetSystemTick, armTicksToNs, armDCacheFlush, armICacheInvalidate, consoleInit, consoleClear, consoleUpdate, consoleExit
- [hcr1/codigo/switch.h #L46-L55](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/switch.h#L46-L55) - Definições e suporte

### Arquivo `text_render.c` (Total: 194 linhas)
- [hcr1/codigo/text_render.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.c#L1-L45) - Funções: text_render_init
- [hcr1/codigo/text_render.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.c#L46-L90) - Funções: next_utf8_char
- [hcr1/codigo/text_render.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.c#L91-L135) - Definições e suporte
- [hcr1/codigo/text_render.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.c#L136-L180) - Definições e suporte
- [hcr1/codigo/text_render.c #L181-L194](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.c#L181-L194) - Definições e suporte

### Arquivo `text_render.h` (Total: 10 linhas)
- [hcr1/codigo/text_render.h #L1-L10](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.h#L1-L10) - Definições e suporte

### Arquivo `util.c` (Total: 18 linhas)
- [hcr1/codigo/util.c #L1-L18](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/util.c#L1-L18) - Funções: tls_setup_guard, ret0

### Arquivo `util.h` (Total: 31 linhas)
- [hcr1/codigo/util.h #L1-L31](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/util.h#L1-L31) - Funções: armGetTlsRw, armSetTlsRw