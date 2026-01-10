# Build Verification Checklist

Run this checklist to verify everything is ready to build.

## Pre-Build Checks

### ✅ Java Installation
```bash
java -version
# Should show: Java 21.x.x
```

### ✅ Gradle Wrapper
```bash
./gradlew --version
# Should show: Gradle 8.x
```

### ✅ File Structure
Verify these exist:
- [ ] build.gradle
- [ ] gradle.properties
- [ ] settings.gradle
- [ ] src/main/java/com/flowpipes/FlowPipesMod.java
- [ ] src/main/resources/fabric.mod.json
- [ ] src/main/resources/assets/flowpipes/blockstates/
- [ ] src/main/resources/assets/flowpipes/models/
- [ ] src/main/resources/assets/flowpipes/lang/
- [ ] src/main/resources/data/flowpipes/recipes/

### ✅ JSON Validation
All these files should exist:
- [ ] 12 blockstate JSONs
- [ ] 18 block model JSONs
- [ ] 12 item model JSONs
- [ ] 12 recipe JSONs
- [ ] 1 language file (en_us.json)
- [ ] 1 mod manifest (fabric.mod.json)

### ✅ Java Classes
All 22 classes should exist:
- [ ] FlowPipesMod.java
- [ ] FlowPipesClient.java
- [ ] ItemPipeBlock.java
- [ ] FluidPipeBlock.java
- [ ] PumpBlock.java
- [ ] SorterBlock.java
- [ ] PipeBlocks.java
- [ ] PipeBlockEntity.java
- [ ] PumpBlockEntity.java
- [ ] SorterBlockEntity.java
- [ ] FlowPayload.java
- [ ] FlowMechanics.java
- [ ] SorterScreenHandler.java
- [ ] SorterScreen.java
- [ ] PipeRenderer.java
- [ ] FlowPipesCommands.java
- [ ] BlockRegistry.java
- [ ] BlockEntityRegistry.java
- [ ] ScreenHandlerRegistry.java
- [ ] PipeTier.java
- [ ] PipeSize.java
- [ ] ItemPayload.java

---

## Build Steps

### Step 1: Clean (Optional)
```bash
./gradlew clean
```

### Step 2: Generate Textures (Optional)
```bash
python generate_textures.py
```
This creates 31 PNG texture files. If you skip this, textures will be missing in-game.

### Step 3: Build
```bash
./gradlew build
```

Expected output:
```
BUILD SUCCESSFUL
Total time: ~30-60 seconds
```

### Step 4: Verify JAR
```bash
ls -la build/libs/flowpipes-1.0.0.jar
# Or on Windows:
dir build\libs\flowpipes-1.0.0.jar
```

---

## Post-Build Checks

### ✅ JAR Contents
The JAR should contain:
- [ ] All 22 compiled classes
- [ ] All 54 JSON files
- [ ] fabric.mod.json manifest
- [ ] Textures (if generated)

### ✅ Size Check
JAR size should be:
- With textures: ~300-500 KB
- Without textures: ~150-250 KB

---

## Installation Verification

### ✅ Install Location
```
%APPDATA%\.minecraft\mods\flowpipes-1.0.0.jar
```

### ✅ Dependencies Installed
- [ ] Fabric Loader 0.15.11+
- [ ] Fabric API 0.111.0+1.21.8
- [ ] Java 21+ JDK

### ✅ Minecraft Launch
- [ ] Create new Fabric profile
- [ ] Launch Minecraft
- [ ] Check logs for "FlowPipes initialized"

---

## In-Game Verification

### ✅ Blocks Appear
- [ ] Item Pipe (Wood) in creative
- [ ] Item Pipe (Iron) in creative
- [ ] Item Pipe (Diamond) in creative
- [ ] Fluid Pipe (Wood) in creative
- [ ] Fluid Pipe (Iron) in creative
- [ ] Fluid Pipe (Diamond) in creative
- [ ] Pump (Wood) in creative
- [ ] Pump (Iron) in creative
- [ ] Pump (Diamond) in creative
- [ ] Sorter (Wood) in creative
- [ ] Sorter (Iron) in creative
- [ ] Sorter (Diamond) in creative

### ✅ Blocks Function
- [ ] Item Pipe places without error
- [ ] Fluid Pipe places without error
- [ ] Pump places without error
- [ ] Sorter places without error
- [ ] All blocks break properly
- [ ] Block items work in inventory

### ✅ GUI System
- [ ] Sorter opens GUI with right-click
- [ ] GUI has 3×3 filter slots
- [ ] GUI has mode toggle button
- [ ] Sorter saves inventory on close

### ✅ Recipes
- [ ] Item Pipe (Wood) craftable (8 items)
- [ ] Item Pipe (Iron) craftable (8 items)
- [ ] Item Pipe (Diamond) craftable (8 items)
- [ ] Fluid Pipe (Wood) craftable (6 items)
- [ ] Fluid Pipe (Iron) craftable (6 items)
- [ ] Fluid Pipe (Diamond) craftable (6 items)
- [ ] Pump (Wood) craftable (1 item)
- [ ] Pump (Iron) craftable (1 item)
- [ ] Pump (Diamond) craftable (1 item)
- [ ] Sorter (Wood) craftable (1 item)
- [ ] Sorter (Iron) craftable (1 item)
- [ ] Sorter (Diamond) craftable (1 item)

### ✅ Commands
- [ ] `/flowpipes animations on` works
- [ ] `/flowpipes animations off` works
- [ ] No error messages in chat

### ✅ Textures (if generated)
- [ ] All blocks have textures
- [ ] No missing texture (magenta/black)
- [ ] Sorter GUI has background texture

---

## Troubleshooting

### Issue: JAR not found after build
**Solution**: Check build.gradle and gradle.properties are correct

### Issue: "Java 21 not found"
**Solution**: Install Java 21 JDK and set JAVA_HOME environment variable

### Issue: Mod doesn't appear in mods menu
**Solution**: 
- Verify fabric.mod.json is valid JSON
- Check mod JAR is in correct location
- Restart Minecraft launcher

### Issue: Blocks don't appear in creative
**Solution**:
- Check latest.log for mod initialization errors
- Verify all blockstate and model JSONs are valid
- Run `./gradlew clean build` to rebuild

### Issue: Textures are missing (magenta/black)
**Solution**:
- Run `python generate_textures.py`
- Run `./gradlew clean build` again
- Reinstall mod JAR

---

## Success Criteria

Project is successfully built when:

✅ `./gradlew build` completes without errors  
✅ JAR file exists at `build/libs/flowpipes-1.0.0.jar`  
✅ JAR is 150+ KB in size  
✅ Mod installs without errors  
✅ Blocks appear in creative inventory  
✅ Recipes work in crafting table  
✅ Sorter GUI opens properly  
✅ No console errors on launch  
✅ `/flowpipes` command works  

---

## Support

If you encounter issues:

1. Check `latest.log` in `.minecraft/logs/`
2. Read relevant section in DEVELOPMENT.md
3. Verify file structure matches FILE_MANIFEST.md
4. Run `./gradlew clean build --refresh-dependencies`

---

## Final Status

When all checks pass:

✅ **Project is production-ready**  
✅ **Mod is ready to distribute**  
✅ **Ready for public use**  

---

**Last Updated**: January 10, 2026  
**Status**: Complete  
**Version**: 1.0.0
