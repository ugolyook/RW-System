MODULES   = core rw-serialization rw-utils init_script rw-cl
OUT_DIR   = out/build
CP        = lib/*
INIT_MAIN = com.sveta.init.InitScript
APP_MAIN  = com.sveta.rwcl.Main

.PHONY: build_init_script run_init_script clean_init_script build_all run_app

build_init_script:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) -cp "$(CP)" -proc:full -processorpath lib/lombok-1.18.38.jar \
		$(shell find $(MODULES) -name '*.java' -path '*src*' -not -path '*/test/*')
	for m in $(MODULES); do \
		[ -d $$m/src/main/resources ] && cp -r $$m/src/main/resources/. $(OUT_DIR)/ || true; \
		[ -d $$m/resources ] && cp -r $$m/resources/. $(OUT_DIR)/ || true; \
	done

run_init_script:
	java -cp "$(OUT_DIR):$(CP)" $(INIT_MAIN)

clean_init_script:
	rm -rf $(OUT_DIR)

build_all: build_init_script

run_app:
	java -cp "$(OUT_DIR):$(CP)" $(APP_MAIN)