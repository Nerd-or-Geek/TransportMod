# FlowPipes - A Gravity-Based Logistics Mod

## Overview

FlowPipes is a production-ready Fabric mod for Minecraft 1.21.8 that implements a gravity-based logistics system. Transport items and fluids (water & lava) through pipes using real-world flow mechanics with head pressure calculations.

## Features

### Blocks

- **Item Pipes** (Wood, Iron, Diamond) - Transport items through networks
- **Fluid Pipes** (Wood, Iron, Diamond) - Transport water and lava only
- **Pumps** (Wood, Iron, Diamond) - Generate head pressure via redstone or water wheels
- **Sorters** (Wood, Iron, Diamond) - Filter items with whitelist/blacklist modes

### Mechanics

All blocks are tiered by material, affecting throughput:
- Wood: 1.0x speed
- Iron: 1.5x speed
- Diamond: 2.5x speed

The limiting factor (pump speed vs. pipe speed) determines actual throughput.

### Pipe Sizes

Upgrade pipes like maps (sneak + right-click):
- 1×1 (default): 1.0x throughput
- 2×2: 2.0x throughput
- 3×3: 3.0x throughput
- 4×4: 4.0x throughput

Target baseline: ~1 stack/second for 1×1 wood pipe.

### Flow Physics

Real head pressure system:
- **Downward flow**: +0.2 head
- **Sideways flow**: -0.1 head
- **Upward flow**: -0.3 head

Payloads require sufficient head pressure to flow upward or sideways. Downward flow always succeeds.

### Pumps

Two activation methods:
1. **Redstone Power** - Active when receiving redstone signal
2. **Water Wheel** - Activates with flowing water (source blocks don't count)

A pump adds 0.5-2.0 head depending on tier.

### Sorters

- Can be placed mid-line or at pipe end
- Accept pipes on all 6 sides
- Whitelist/Blacklist filtering
- 9-slot configuration GUI

### Animations & Commands

Client-side animated item movement:
```
/flowpipes animations on    # Enable animations
/flowpipes animations off   # Disable animations
```

## Crafting

All pipes craft in rings (8 items) except fluid pipes (6 items) and sorters/pumps (1 item).

### Recipes

**Wood Pipes**: Oak planks in 3×3 ring
**Iron Pipes**: Iron ingots in 3×3 ring
**Diamond Pipes**: Diamonds in 3×3 ring
**Pumps**: Cross pattern with redstone at center
**Sorters**: Cross pattern with center block of matching tier

## Project Structure

```
src/main/java/com/flowpipes/
├── FlowPipesMod.java                    # Main entry point
├── client/
│   ├── FlowPipesClient.java             # Client init
│   ├── screen/
│   │   └── SorterScreen.java            # Sorter GUI
│   └── render/
│       └── PipeRenderer.java            # Animation rendering
├── block/
│   ├── PipeBlocks.java                  # Block registry holder
│   ├── ItemPipeBlock.java               # Item pipe
│   ├── FluidPipeBlock.java              # Fluid pipe
│   ├── PumpBlock.java                   # Pump
│   └── SorterBlock.java                 # Sorter
├── blockentity/
│   ├── PipeBlockEntity.java             # Pipe flow logic
│   ├── PumpBlockEntity.java             # Pump state
│   └── SorterBlockEntity.java           # Sorter inventory + filtering
├── command/
│   └── FlowPipesCommands.java           # Animation toggle command
├── flow/
│   ├── FlowPayload.java                 # Item/fluid wrapper
│   └── FlowMechanics.java               # Head pressure physics
├── screen/
│   └── SorterScreenHandler.java         # Sorter GUI handler
├── registry/
│   ├── BlockRegistry.java               # Block registration
│   ├── BlockEntityRegistry.java         # BlockEntity registration
│   └── ScreenHandlerRegistry.java       # ScreenHandler registration
└── util/
    ├── PipeTier.java                    # Tier enum (Wood/Iron/Diamond)
    └── PipeSize.java                    # Size enum (1x1/2x2/3x3/4x4)
```

## Textures

Texture placeholders are located in:
```
src/main/resources/assets/flowpipes/textures/
├── block/                   # Block textures (16×16)
│   ├── item_pipe_*.png
│   ├── fluid_pipe_*.png
│   ├── pump_*.png
│   ├── pump_*_active.png
│   └── sorter_*.png
├── item/                    # Item textures (16×16)
│   └── [same as block]
└── gui/
    └── sorter_gui.png       # Sorter GUI texture (176×222)

```

### Texture Generation

For vanilla-style textures, use a pixel art tool or Minecraft texture pack creator:
- **Pipes**: Simple hollow squares, color-coded by material
  - Wood: Oak brown (#8B7355)
  - Iron: Steel gray (#A8A8A8)
  - Diamond: Bright cyan (#55FFFF)
- **Pumps**: Directional block with animated state
- **Sorters**: Grid pattern with center outlet
- **GUI**: Standard 256×256 GUI background

Alternatively, use texture-generating mods or AI image tools to create 16×16 blocks in the Minecraft style.

## Building & Installation

### Prerequisites
- Java 21 JDK
- Gradle (included in project)

### Build

```bash
cd c:\Users\cadet\Documents\GitHub\My-First-MC-Mod
./gradlew build
```

Output JAR: `build/libs/flowpipes-1.0.0.jar`

### Install

1. Place the JAR in your mods folder (`%APPDATA%\.minecraft\mods`)
2. Install Fabric Loader 0.15.11+
3. Install Fabric API for 1.21.8
4. Launch with the Fabric profile

## Dependencies

- **Minecraft**: 1.21.8
- **Fabric Loader**: 0.15.11+
- **Fabric API**: 0.111.0+1.21.8
- **Java**: 21+

## Implementation Notes

### Flow Mechanics

Flow is tick-based and server-authoritative:
- Each pipe BlockEntity ticks 20/sec
- Payloads carry head pressure and direction
- Physics applied each tick in `PipeBlockEntity.tick()`

### Sorter Filtering

Sorters use a 9-slot inventory for item filters:
- **Whitelist**: Items matching inventory pass through designated output
- **Blacklist**: Items NOT matching inventory pass through

### Pump Water Detection

Water wheel detection checks for flowing water via fluid state (not source blocks).

## Future Enhancement Opportunities

- Multi-item sorting with colored filters
- Advanced pathfinding for complex networks
- Fluid fluid mixing (water + lava = obsidian)
- Dynamic pipe damage under pressure
- Belt conveyors for non-networked transport
- Filter extraction sidecar items

## License

MIT License - See LICENSE file for details

## Building from Source

1. Clone repository
2. Run `./gradlew build`
3. Output in `build/libs/`

For IntelliJ IDEA:
```bash
./gradlew idea
```

Then open in IDE and run configurations will be auto-generated.

## Troubleshooting

### Items not flowing
- Check pump has power (redstone or water wheel)
- Verify pipe connections (look for missing sides)
- Ensure destination inventory has space
- Check sorter filter isn't blocking items

### Animation not visible
- Run `/flowpipes animations on`
- Ensure video settings aren't set to minimum
- Check client-side mod loading

### Compiling issues
- Ensure Java 21 JDK is installed
- Run `./gradlew clean build`
- Check all dependencies in gradle.properties

## Credits

Created with Fabric API and Loom toolchain for Minecraft 1.21.8.
