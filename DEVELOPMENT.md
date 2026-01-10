# FlowPipes Development Guide

## Project Structure Overview

```
src/main/java/com/flowpipes/
├── FlowPipesMod.java                  Main entry point - initializes registries
├── client/
│   ├── FlowPipesClient.java           Client initialization
│   ├── render/
│   │   └── PipeRenderer.java          Animation rendering hooks
│   └── screen/
│       └── SorterScreen.java          Sorter GUI implementation
├── block/
│   ├── PipeBlocks.java                Holder for all block static references
│   ├── ItemPipeBlock.java             Item transport block
│   ├── FluidPipeBlock.java            Fluid transport block
│   ├── PumpBlock.java                 Pump with redstone states
│   └── SorterBlock.java               Sorter with inventory
├── blockentity/
│   ├── PipeBlockEntity.java           Flow payload processing
│   ├── PumpBlockEntity.java           Pump activation tracking
│   └── SorterBlockEntity.java         Sorter inventory + filter logic
├── command/
│   └── FlowPipesCommands.java         /flowpipes animations command
├── flow/
│   ├── FlowPayload.java               Item/fluid wrapper
│   └── FlowMechanics.java             Head pressure physics
├── registry/
│   ├── BlockRegistry.java             Register all 12 blocks
│   ├── BlockEntityRegistry.java       Register 4 BlockEntity types
│   └── ScreenHandlerRegistry.java     Register sorter handler
├── screen/
│   └── SorterScreenHandler.java       Sorter inventory handler
└── util/
    ├── PipeTier.java                  Wood/Iron/Diamond enum
    ├── PipeSize.java                  1x1/2x2/3x3/4x4 enum
    └── ItemPayload.java               ItemStack wrapper
```

## Code Entry Points

### Server-Side (Server Ticks Every 20ms)

1. **FlowPipesMod.onInitialize()**
   - Calls BlockRegistry, BlockEntityRegistry, ScreenHandlerRegistry
   - Registers commands

2. **BlockRegistry.registerBlocks()**
   - Creates block instances
   - Registers them with Minecraft

3. **BlockEntityRegistry.registerBlockEntities()**
   - Links BlockEntities to blocks
   - Creates factory lambdas for instantiation

4. **PipeBlockEntity.tick()** (every tick)
   - Processes all payloads in pipe
   - Applies flow mechanics
   - Removes stuck payloads
   - **KEY LOCATION FOR FLOW LOGIC**

### Client-Side (Client Ticks Every Frame)

1. **FlowPipesClient.onInitializeClient()**
   - Calls PipeRenderer.registerRenderers()

2. **PipeRenderer.renderPipeItems()** (per frame)
   - Renders flowing item animations
   - Only when animationsEnabled = true
   - Called from world render event

3. **SorterScreen.render()** (per frame)
   - Renders GUI background and slots
   - Renders mode toggle button

## Key Algorithms

### Flow Mechanics (FlowMechanics.java)

```java
float headChange = switch(direction) {
    case DOWN -> DOWNWARD_GAIN;      // +0.2
    case UP -> -UPWARD_COST;          // -0.3
    default -> -SIDEWAYS_COST;        // -0.1
};
```

**Usage**: Each tick, payload head is adjusted by direction cost. If head <= 0 and direction is up/sideways, flow stops.

### Throughput Calculation

```
items_per_tick = base_rate × min(pump_tier, pipe_tier) × pipe_size
               = 3.2 × tier_speed × size_multiplier
```

**Example**: Wood pipe (1.0) + Wood pump (1.0) + 2×2 size (2.0) = 6.4 items/tick = ~320 items/sec

### Sorter Filtering

```java
boolean isWhitelisted = sorter.inventory.contains(itemType);
boolean shouldPass = isWhitelisted == sorter.isWhitelist;
```

If whitelist mode and item in filter → pass
If blacklist mode and item NOT in filter → pass

## Extending the Mod

### Add a New Tier

1. **Edit `PipeTier.java`**:
   ```java
   NETHERITE(3.5f, "netherite");
   ```

2. **Edit `BlockRegistry.java`** in `registerBlocks()`:
   ```java
   PipeBlocks.ITEM_PIPE_NETHERITE = registerBlock("item_pipe_netherite",
       new ItemPipeBlock(PipeTier.NETHERITE));
   ```

3. **Create block classes**:
   - ItemPipeBlock (already dynamic, inherits tier)

4. **Add to `PipeBlocks.java`**:
   ```java
   public static Block ITEM_PIPE_NETHERITE;
   ```

5. **Create JSON assets**:
   - blockstates/item_pipe_netherite.json
   - models/block/item_pipe_netherite.json
   - models/item/item_pipe_netherite.json

6. **Create recipe**:
   - data/flowpipes/recipes/item_pipe_netherite.json

### Add a New Sorter Mode

1. **Edit `SorterBlockEntity.java`**:
   ```java
   public enum SortMode { WHITELIST, BLACKLIST, PRIORITY, ROUND_ROBIN }
   private SortMode mode = SortMode.WHITELIST;
   ```

2. **Update filtering logic**:
   ```java
   public boolean shouldPass(ItemStack item) {
       return switch(mode) {
           case WHITELIST -> inventory.contains(item);
           case BLACKLIST -> !inventory.contains(item);
           case PRIORITY -> checkPriority(item);
           // ... etc
       };
   }
   ```

3. **Update GUI**:
   - Cycle through modes in SorterScreen
   - Display current mode

### Add a New Block Type

1. **Create `YourBlock.java` extending `Block`**
2. **Register in `BlockRegistry.registerBlocks()`**
3. **Create BlockEntity if needed**
4. **Register in `BlockEntityRegistry`**
5. **Add JSON assets** (blockstate, models)
6. **Add recipe** in data/flowpipes/recipes/

## Debug Techniques

### Enable Logging

```java
// In any class
private static final Logger LOGGER = LoggerFactory.getLogger("flowpipes");

// Usage
LOGGER.info("Pump activated at {}", pos);
LOGGER.warn("No head pressure!");
```

Check logs in: `logs/latest.log`

### Inspect Block State

```java
BlockState state = world.getBlockState(pos);
boolean powered = state.get(PumpBlock.POWERED);
```

### Inspect BlockEntity

```java
BlockEntity entity = world.getBlockEntity(pos);
if (entity instanceof PipeBlockEntity pipe) {
    int payloadCount = pipe.getPayloads().size();
    LOGGER.info("Pipe has {} payloads", payloadCount);
}
```

### Track Flow Payloads

Add to `PipeBlockEntity.tick()`:
```java
if (world.random.nextInt(100) < 5) { // Log 5% of ticks
    LOGGER.info("[{}] Processing {} payloads at head pressure {}",
        pos, payloads.size(), 
        payloads.isEmpty() ? 0 : payloads.get(0).getHeadPressure());
}
```

## Testing Checklist

- [ ] Block places and breaks without errors
- [ ] Block properties update (powered, connected sides)
- [ ] GUI opens with correct title
- [ ] Inventory slots work
- [ ] Recipes craft
- [ ] Blockstate JSON validates
- [ ] Models render without black/magenta
- [ ] Commands execute without errors
- [ ] No error spam in logs
- [ ] Payloads process (add logging to verify)

## Common Issues & Solutions

### Issue: "Block not registering"

**Cause**: Block not added to registry
**Solution**: Check `BlockRegistry.registerBlocks()` includes your block

```java
PipeBlocks.YOUR_BLOCK = registerBlock("your_block", new YourBlock());
```

### Issue: "NBT data lost on reload"

**Cause**: BlockEntity not implementing `writeNbt()`/`readNbt()`
**Solution**: Implement both methods:

```java
@Override
protected void writeNbt(NbtCompound nbt) {
    super.writeNbt(nbt);
    nbt.putInt("my_value", myValue);
}

@Override
public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    myValue = nbt.getInt("my_value");
}
```

### Issue: "GUI doesn't sync to client"

**Cause**: Missing packet or ScreenHandler implementation
**Solution**: Ensure ScreenHandler properly implements `canUse()` and slots

### Issue: "Animations don't show"

**Cause**: `animationsEnabled = false` or `PipeRenderer.renderPipeItems()` not called
**Solution**: 
1. Run `/flowpipes animations on`
2. Check that rendering is hooked into world render events
3. Verify animation logic in `PipeRenderer.java`

### Issue: "Items disappear"

**Cause**: Payloads stuck in pipe (no space in destination)
**Solution**: Add destination inventory check in `PipeBlockEntity.tick()`

```java
BlockEntity dest = world.getBlockEntity(pos.offset(payload.getDirection()));
if (dest instanceof Inventory inv && inv.isEmpty()) {
    // Can insert
}
```

## Performance Optimization

### Reduce Payload Checks

Instead of checking every payload every tick, use a queue:
```java
private Queue<FlowPayload> processingQueue = new LinkedList<>();

public void tick() {
    while (!processingQueue.isEmpty()) {
        FlowPayload p = processingQueue.poll();
        if (canFlow(p)) {
            movePayload(p);
        }
    }
}
```

### Cache Block States

```java
private final BlockState cachedState;

public void updateConnections() {
    // Only rebuild when neighbors change
    if (!state.equals(cachedState)) {
        rebuildModel();
        cachedState = state;
    }
}
```

### Lazy Model Rebuilding

Only regenerate block models when needed:
- On neighborUpdate
- On block state change
- Not every tick

## Version Targets

- **Minecraft**: 1.21.8
- **Fabric Loader**: 0.15.11+
- **Fabric API**: 0.111.0+1.21.8
- **Java**: 21+

## Useful Resources

- Yarn Mappings: `https://github.com/FabricMC/yarn`
- Fabric Wiki: `https://fabricmc.net/wiki`
- Minecraft Protocol: `https://wiki.vg`
- Block Properties: `net.minecraft.state.property.*`
- Registries: `net.minecraft.registry.Registries`

## Building for Different Targets

### Build normal JAR:
```bash
./gradlew build
```

### Build with sources:
```bash
./gradlew build -PpublishSources
```

### Clean build:
```bash
./gradlew clean build
```

### Run dev environment:
```bash
./gradlew runClient    # Single-player test
./gradlew runServer    # Multiplayer test
```

## Contributing Guidelines

1. Follow existing code style (3-space indent)
2. Add logging for debugging: `LOGGER.info()`
3. Validate all JSON files
4. Test on clean world
5. Document non-obvious logic
6. Keep flow mechanics separate from I/O
7. Server-authoritative design (trust server, verify on client)

## License

MIT License - See LICENSE file for full terms.

---

**Last Updated**: January 10, 2026  
**Version**: 1.0.0  
**Status**: Production Ready
