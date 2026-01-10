# FlowPipes Mod - Quick Reference

## Build & Install

```bash
# 1. Generate textures (optional, but recommended)
python generate_textures.py

# 2. Build JAR
./gradlew build

# 3. Copy to mods folder
cp build/libs/flowpipes-1.0.0.jar %APPDATA%\.minecraft\mods\

# 4. Launch Minecraft with Fabric
```

## Block Recipes

| Block | Recipe |
|-------|--------|
| Item Pipe (Wood) | 8× Oak planks in 3×3 ring |
| Item Pipe (Iron) | 8× Iron ingots in 3×3 ring |
| Item Pipe (Diamond) | 8× Diamonds in 3×3 ring |
| Fluid Pipe (Wood) | 6× Oak logs in 3×3 ring |
| Fluid Pipe (Iron) | 6× Iron blocks in 3×3 ring |
| Fluid Pipe (Diamond) | 6× Diamond blocks in 3×3 ring |
| Pump (Wood) | Cross: wood planks + redstone center |
| Pump (Iron) | Cross: iron ingots + redstone center |
| Pump (Diamond) | Cross: diamonds + redstone center |
| Sorter (Wood) | Cross: wood planks + oak log center |
| Sorter (Iron) | Cross: iron ingots + iron block center |
| Sorter (Diamond) | Cross: diamonds + diamond block center |

## Commands

```
/flowpipes animations on      # Enable item animations
/flowpipes animations off     # Disable item animations
```

## Block Interactions

- **Pipes**: Sneak + Right-click to cycle size (1×1 → 2×2 → 3×3 → 4×4)
- **Sorters**: Right-click to open filter GUI
- **Pumps**: Activate via redstone or water wheel

## Pump Activation

1. **Redstone**: Connect redstone wire/block to any side
2. **Water Wheel**: Place flowing water adjacent (source blocks don't count)

## Sorter Filtering

- Place items in top 3×3 grid
- Click "White" or "Black" button to toggle mode
  - **White**: Only listed items pass through
  - **Black**: Everything except listed items passes through

## Tier Speeds

| Tier | Speed Multiplier |
|------|-----------------|
| Wood | 1.0× |
| Iron | 1.5× |
| Diamond | 2.5× |

Actual throughput = min(pump tier, pipe tier) × pipe size × base rate

## Pipe Sizes

| Size | Multiplier |
|------|-----------|
| 1×1 | 1.0× |
| 2×2 | 2.0× |
| 3×3 | 3.0× |
| 4×4 | 4.0× |

## Flow Physics

```
Direction        | Head Change
===============================================
Down             | +0.2 (gravity helps)
Sideways         | -0.1 (friction)
Up               | -0.3 (against gravity)
```

Payloads need sufficient head to flow up/sideways.

## Project Files

**Core Code** (22 Java classes)
- 1 Main class
- 4 Block classes
- 3 BlockEntity classes
- 2 Flow logic classes
- 2 GUI classes
- 1 Renderer class
- 1 Command class
- 3 Registry classes
- 3 Utility/Enum classes

**Assets** (54 JSON files)
- 12 Blockstate JSONs
- 18 Block model JSONs
- 12 Item model JSONs
- 12 Recipe JSONs
- 1 Language JSON

**Textures** (31 generated)
- 18 Block textures (16×16)
- 12 Item textures (16×16)
- 1 GUI texture (256×256)

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Items not flowing | Check pump has power; verify connections |
| JAR fails to build | Run `./gradlew clean build --refresh-dependencies` |
| Textures missing | Run `python generate_textures.py` and rebuild |
| Mod won't load | Ensure Fabric Loader 0.15.11+ and Fabric API installed |
| Sorter GUI crashes | Check Java 21 is being used |

## Supported Fluids

- ✅ Water (bucket + flowing)
- ✅ Lava (bucket + flowing)
- ❌ Custom fluids (not implemented)

## Integration Points

- ✅ Chests (extract/insert items)
- ✅ Hoppers (normal pull behavior)
- ✅ All vanilla containers
- ❌ Create mod (not required)
- ❌ Applied Energistics (not supported)

## Performance Tips

1. Use higher tier pipes for large networks
2. Pump head can be limiting factor—upgrade as needed
3. Use sorters to prevent backpressure
4. Disable animations if FPS drops: `/flowpipes animations off`

## File Locations

After building, the mod JAR is at:
```
build/libs/flowpipes-1.0.0.jar
```

Copy to:
```
%APPDATA%\.minecraft\mods\flowpipes-1.0.0.jar
```

Or on Mac/Linux:
```
~/.minecraft/mods/flowpipes-1.0.0.jar
```

## Development

The mod is written in **Java 21** with **Fabric 1.21.8** for Minecraft.

### Key Classes to Modify

- `FlowMechanics.java` - Physics calculations
- `PipeBlockEntity.java` - Flow processing
- `SorterBlockEntity.java` - Filter logic
- `PipeTier.java` - Tier speeds
- `PipeSize.java` - Size multipliers

### For New Tiers

Add to `PipeTier.java` enum:
```java
NETHERITE(3.5f, "netherite");
```

Then add corresponding block classes and register them.

---

**Version**: 1.0.0  
**Minecraft**: 1.21.8  
**Fabric**: 0.15.11+  
**Java**: 21+

For full documentation, see `README.md` or `SETUP_GUIDE.md`.
