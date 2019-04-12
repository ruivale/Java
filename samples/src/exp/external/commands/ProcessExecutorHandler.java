
package exp.external.commands;

// interface.
public interface ProcessExecutorHandler {
    void onStandardOutput(String msg);
    void onStandardError(String msg);
}
